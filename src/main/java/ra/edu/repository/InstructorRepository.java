package ra.edu.repository;

import org.springframework.stereotype.Repository;
import ra.edu.model.Instructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class InstructorRepository {
    private final List<Instructor> instructors = new ArrayList<>();

    public List<Instructor> findAll() {
        return instructors;
    }

    public Optional<Instructor> findById(int id) {
        return instructors.stream()
                .filter(instructor -> instructor.getId() == id)
                .findFirst();
    }

    public Instructor create(Instructor instructor) {
        instructors.add(instructor);
        return instructor;
    }

    public Instructor update(Instructor instructor, int id) {
        Instructor existing = findById(id)
                .orElseThrow(() -> new RuntimeException("Giảng viên không tồn tại"));
        existing.setName(instructor.getName());
        existing.setEmail(instructor.getEmail());
        return existing;
    }

    public Instructor delete(int id) {
        Instructor existing = findById(id)
                .orElseThrow(() -> new RuntimeException("Giảng viên không tồn tại"));
        instructors.remove(existing);
        return existing;
    }
}