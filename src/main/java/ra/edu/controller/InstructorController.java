package ra.edu.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.edu.dto.ApiResponse;
import ra.edu.model.Instructor;
import ra.edu.service.InstructorService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/instructors")
@RequiredArgsConstructor
public class InstructorController {
    private final InstructorService instructorService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Instructor>>> getListInstructors() {
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Fetched instructors successfully", instructorService.findAllInstructors())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Instructor>> getInstructorById(@PathVariable int id) {
        try {
            Instructor instructor = instructorService.findInstructorById(id);
            return ResponseEntity.ok(
                    new ApiResponse<>(true, "Fetched instructor successfully", instructor)
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Instructor>> saveInstructor(@RequestBody Instructor instructor) {
        Instructor created = instructorService.createInstructor(instructor);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "Created instructor successfully", created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Instructor>> updateInstructor(@RequestBody Instructor instructor, @PathVariable int id) {
        try {
            Instructor updatedInstructor = instructorService.updateInstructor(instructor, id);
            return ResponseEntity.ok(
                    new ApiResponse<>(true, "Instructor updated", instructor)
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Instructor>> deleteInstructor(@PathVariable int id) {
        try {
            Instructor deleted = instructorService.deleteInstructor(id);
            return ResponseEntity.ok(
                    new ApiResponse<>(true, "Instructor deleted", deleted)
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }
}