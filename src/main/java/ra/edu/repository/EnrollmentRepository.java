package ra.edu.repository;

import org.springframework.stereotype.Repository;
import ra.edu.model.Enrollment;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class EnrollmentRepository {
    private final List<Enrollment> enrollments = new ArrayList<>();

    public List<Enrollment> findAll() {
        return enrollments;
    }

    public Optional<Enrollment> findById(int id) {
        return enrollments.stream()
                .filter(enrollment -> enrollment.getId() == id)
                .findFirst();
    }

    public Enrollment create(Enrollment enrollment) {
        enrollments.add(enrollment);
        return enrollment;
    }

    public Enrollment update(Enrollment enrollment, int id) {
        Enrollment existing = findById(id)
                .orElseThrow(() -> new RuntimeException("Không có lượt đăng ký"));
        existing.setStudentName(enrollment.getStudentName());
        existing.setCourseId(enrollment.getCourseId());
        return existing;
    }

    public Enrollment delete(int id) {
        Enrollment existing = findById(id)
                .orElseThrow(() -> new RuntimeException("Không có lượt đăng ký"));
        enrollments.remove(existing);
        return existing;
    }
}