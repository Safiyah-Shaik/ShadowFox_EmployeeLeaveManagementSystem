package com.shadowfox.employeeleavemanagement.service;

import com.shadowfox.employeeleavemanagement.entity.Employee;
import com.shadowfox.employeeleavemanagement.entity.LeaveRequest;
import com.shadowfox.employeeleavemanagement.entity.LeaveStatus;
import com.shadowfox.employeeleavemanagement.repository.EmployeeRepository;
import com.shadowfox.employeeleavemanagement.repository.LeaveRequestRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class LeaveRequestService {

    private final LeaveRequestRepository leaveRequestRepository;
    private final EmployeeRepository employeeRepository;

    public LeaveRequestService(
            LeaveRequestRepository leaveRequestRepository,
            EmployeeRepository employeeRepository) {
        this.leaveRequestRepository = leaveRequestRepository;
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    public LeaveRequest applyForLeave(Long employeeId, LeaveRequest request) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Employee not found"));

        if (request.getStartDate() == null || request.getEndDate() == null) {
            throw new IllegalArgumentException("Leave dates are required");
        }

        if (request.getStartDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException(
                    "Leave start date cannot be in the past");
        }

        if (request.getEndDate().isBefore(request.getStartDate())) {
            throw new IllegalArgumentException(
                    "End date cannot be before start date");
        }

        request.setEmployee(employee);
        request.setStatus(LeaveStatus.PENDING);

        return leaveRequestRepository.save(request);
    }

    @Transactional(readOnly = true)
public List<LeaveRequest> getEmployeeLeaveHistory(Long employeeId) {
    if (!employeeRepository.existsById(employeeId)) {
        throw new IllegalArgumentException("Employee not found");
    }

    List<LeaveRequest> requests =
            leaveRequestRepository.findByEmployeeId(employeeId);

    for (LeaveRequest request : requests) {
        request.getEmployee().getName();
        if (request.getReviewedBy() != null) {
            request.getReviewedBy().getName();
        }
    }

    return requests;
}

    @Transactional(readOnly = true)
    public List<LeaveRequest> getLeaveRequestsByStatus(LeaveStatus status) {
        return leaveRequestRepository.findByStatus(status);
    }
        @Transactional
        public LeaveRequest reviewLeaveRequest(
            Long leaveRequestId,
            Long adminId,
            boolean approve,
            String comment) {

        Employee admin = employeeRepository.findById(adminId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Admin not found"));

        if (admin.getRole() != com.shadowfox.employeeleavemanagement.entity.Role.ADMIN) {
            throw new IllegalArgumentException(
                    "Only admins can review leave requests");
        }

        LeaveRequest request = leaveRequestRepository.findById(leaveRequestId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Leave request not found"));

        if (request.getStatus() != LeaveStatus.PENDING) {
            throw new IllegalArgumentException(
                    "Only pending leave requests can be reviewed");
        }

        
            request.setStatus(approve ? LeaveStatus.APPROVED : LeaveStatus.REJECTED);
            request.setReviewedBy(admin);
            request.setReviewComment(comment);

            LeaveRequest savedRequest = leaveRequestRepository.save(request);

            // Load related employee details before returning the response
            savedRequest.getEmployee().getName();
            savedRequest.getReviewedBy().getName();

        return savedRequest;

    }
}
