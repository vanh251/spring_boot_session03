package ra.edu.repository;

import org.springframework.stereotype.Repository;
import ra.edu.model.Course;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CourseRepository {
    private final List<Course> courses = new ArrayList<>();

    public List<Course> findAll() {
        return courses;
    }

    public Optional<Course> findById(int id) {
        return courses.stream()
                .filter(course -> course.getId() == id)
                .findFirst();
    }


    public Course create(Course course) {
        courses.add(course);
        return course;
    }

    public Course update(Course course, int id) {
        Course existing = findById(id).orElseThrow(() -> new RuntimeException("Khóa học không tồn tại"));
        existing.setTitle(course.getTitle());
        existing.setStatus(course.getStatus());
        existing.setInstructorId(course.getInstructorId());

        return existing;
    }

    public Course delete(int id) {
        Course existing = findById(id)
                .orElseThrow(() -> new RuntimeException("Khóa học không tồn tại"));
        courses.remove(existing);
        return existing;
    }
}