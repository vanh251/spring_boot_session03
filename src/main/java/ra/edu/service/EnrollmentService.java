package ra.edu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ra.edu.model.Enrollment;
import ra.edu.repository.EnrollmentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;

    public List<Enrollment> findAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    public Enrollment findEnrollmentById(int id) {
        return enrollmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không có lượt đăng ký"));
    }

    public Enrollment createEnrollment(Enrollment enrollment) {
        return enrollmentRepository.create(enrollment);
    }

    public Enrollment updateEnrollment(Enrollment enrollment, int id) {
        return enrollmentRepository.update(enrollment, id);
    }

    public Enrollment deleteEnrollment(int id) {
        return enrollmentRepository.delete(id);
    }
}