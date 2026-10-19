package com.shadowfox.employeeleavemanagement.controller;

import com.shadowfox.employeeleavemanagement.entity.LeaveRequest;
import com.shadowfox.employeeleavemanagement.entity.LeaveStatus;
import com.shadowfox.employeeleavemanagement.service.LeaveRequestService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leaves")
public class LeaveRequestController {

    private final LeaveRequestService leaveRequestService;

    public LeaveRequestController(LeaveRequestService leaveRequestService) {
        this.leaveRequestService = leaveRequestService;
    }

    @PostMapping("/employee/{employeeId}")
    @ResponseStatus(HttpStatus.CREATED)
    public LeaveRequest applyForLeave(
            @PathVariable Long employeeId,
            @Valid @RequestBody LeaveRequest request) {
        return leaveRequestService.applyForLeave(employeeId, request);
    }

    @GetMapping("/employee/{employeeId}")
    public List<LeaveRequest> getEmployeeLeaveHistory(
            @PathVariable Long employeeId) {
        return leaveRequestService.getEmployeeLeaveHistory(employeeId);
    }

    @GetMapping("/status/{status}")
    public List<LeaveRequest> getLeaveRequestsByStatus(
            @PathVariable LeaveStatus status) {
        return leaveRequestService.getLeaveRequestsByStatus(status);
    }

    @PutMapping("/{leaveRequestId}/review")
    public LeaveRequest reviewLeaveRequest(
            @PathVariable Long leaveRequestId,
            @RequestParam Long adminId,
            @RequestParam boolean approve,
            @RequestParam(required = false) String comment) {
        return leaveRequestService.reviewLeaveRequest(
                leaveRequestId, adminId, approve, comment);
    }
}
