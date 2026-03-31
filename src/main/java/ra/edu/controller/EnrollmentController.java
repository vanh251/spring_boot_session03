package ra.edu.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.edu.dto.ApiResponse;
import ra.edu.model.Enrollment;
import ra.edu.service.EnrollmentService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Enrollment>>> getAllEnrollments(){
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Fetched enrollments successfully", enrollmentService.findAllEnrollments())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Enrollment>> getEnrollmentById(@PathVariable int id){
        try {
            Enrollment enrollment = enrollmentService.findEnrollmentById(id);
            return ResponseEntity.ok(
                    new ApiResponse<>(true, "Fetched enrollment successfully", enrollment)
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Enrollment>> saveEnrollment(@RequestBody Enrollment enrollment){
        Enrollment created = enrollmentService.createEnrollment(enrollment);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "Created enrollment successfully", created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Enrollment>> updateEnrollment(@PathVariable int id, @RequestBody Enrollment enrollment){
        try {
            Enrollment updatedEnrollment = enrollmentService.updateEnrollment(enrollment, id);
            return ResponseEntity.ok(
                    new ApiResponse<>(true, "Enrollment updated", enrollment)
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Enrollment>> deleteEnrollment(@PathVariable int id){
        try {
            Enrollment deleted = enrollmentService.deleteEnrollment(id);
            return ResponseEntity.ok(
                    new ApiResponse<>(true, "Enrollment deleted", deleted)
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }
}