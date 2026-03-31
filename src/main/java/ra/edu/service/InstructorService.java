package ra.edu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ra.edu.model.Instructor;
import ra.edu.repository.InstructorRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InstructorService {
    private final InstructorRepository instructorRepository;

    public List<Instructor> findAllInstructors() {
        return instructorRepository.findAll();
    }

    public Instructor findInstructorById(int id) {
        return instructorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Giảng viên không tồn tại"));
    }

    public Instructor createInstructor(Instructor instructor) {
        return instructorRepository.create(instructor);
    }

    public Instructor updateInstructor(Instructor instructor, int id) {
        return instructorRepository.update(instructor, id);
    }

    public Instructor deleteInstructor(int id) {
        return instructorRepository.delete(id);
    }
}