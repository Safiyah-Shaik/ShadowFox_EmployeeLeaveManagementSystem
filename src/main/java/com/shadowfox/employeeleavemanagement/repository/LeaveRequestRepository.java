package com.shadowfox.employeeleavemanagement.repository;

import com.shadowfox.employeeleavemanagement.entity.LeaveRequest;
import com.shadowfox.employeeleavemanagement.entity.LeaveStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {

    List<LeaveRequest> findByEmployeeId(Long employeeId);

    List<LeaveRequest> findByStatus(LeaveStatus status);

    List<LeaveRequest> findByEmployeeIdAndStatus(
            Long employeeId, LeaveStatus status);
}
