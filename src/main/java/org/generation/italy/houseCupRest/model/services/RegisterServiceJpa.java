package org.generation.italy.houseCupRest.model.services;

import org.generation.italy.houseCupRest.model.entities.Course;
import org.generation.italy.houseCupRest.model.entities.House;
import org.generation.italy.houseCupRest.model.entities.Student;
import org.generation.italy.houseCupRest.model.entities.Teacher;
import org.generation.italy.houseCupRest.model.exceptions.IdDoesNotExistException;
import org.generation.italy.houseCupRest.model.exceptions.ResourceNotFoundException;
import org.generation.italy.houseCupRest.model.repositories.CourseRepositoryJpa;
import org.generation.italy.houseCupRest.model.repositories.HouseRepositoryJpa;
import org.generation.italy.houseCupRest.model.repositories.StudentRepositoryJpa;
import org.generation.italy.houseCupRest.model.repositories.TeacherRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RegisterServiceJpa implements RegisterService{
    private CourseRepositoryJpa courseRepo;
    private HouseRepositoryJpa houseRepo;
    private StudentRepositoryJpa studentRepo;
    private TeacherRepositoryJpa teacherRepo;

    @Autowired
    public RegisterServiceJpa(CourseRepositoryJpa courseRepo, HouseRepositoryJpa houseRepo, StudentRepositoryJpa studentRepo, TeacherRepositoryJpa teacherRepo){
        this.courseRepo = courseRepo;
        this.houseRepo = houseRepo;
        this.studentRepo = studentRepo;
        this.teacherRepo = teacherRepo;
    }

    @Override
    public List<Course> findAllCourses() {
        return courseRepo.findAll();
    }

    @Override
    public List<House> getAllHouses() {
        return houseRepo.findAll();
    }

    @Override
    public Optional<Course> findCourseById(long id) {
        return courseRepo.findById(id);
    }

    @Override
    public Optional<House> findHouseById(long id) {
        return houseRepo.findById(id);
    }

    @Override
    public List<Teacher> findAllTeachers() { // aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaah
        return teacherRepo.findAll();
    }

    @Override
    public List<Student> findAllStudents() {
        return studentRepo.findAll();
    }

    @Override
    public Student saveStudent(Student s) {
        return studentRepo.save(s);
    }

    @Override
    public Optional<Student> findStudentById(long id) {
        return studentRepo.findById(id);
    }

    @Override
    public Optional<Teacher> findTeacherById(long id) {
        return teacherRepo.findById(id);
    }

    @Override
    public Optional <Course> deleteCourseById(long id) {
//        if(courseRepo.findById(id).isPresent()){
//            Optional <Course> courseFidedById = courseRepo.findById(id);
//            courseRepo.deleteById(id);
//            return courseFidedById;
//        }
//        return Optional.empty();
            Optional<Course> oC = courseRepo.findById(id);
            oC.ifPresent(course -> courseRepo.delete(course));
            return oC;
    }


    @Override
    public Optional<Course> updateCourse(Course course) {
        Optional<Course> oC = courseRepo.findById(course.getId());
        Course oldCourse = null;
        if(oC.isPresent()){
            oldCourse = new Course(oC.get().getId(),oC.get().getClassName(),oC.get().getStartDate(),oC.get().getEndDate());
            courseRepo.save(course);
        }
        return Optional.ofNullable(oldCourse);
    }

    @Override
    public Course create(Course course) {
        Course courseSaved = courseRepo.save(course);
        return courseSaved;
    }

    @Override
    public List<Course> findActiveCourseByNamesContains(String className) {
        return courseRepo.findByStartDateBeforeAndEndDateAfterAndClassNameContains(LocalDate.now(),LocalDate.now(), className);
    }

    @Override
    public List<Course> findByClassNameContains(String className) {
        return courseRepo.findByClassNameContains(className);
    }

    @Override
    public List<Course> findActiveCourses() {
        return courseRepo.findByStartDateBeforeAndEndDateAfter(LocalDate.now(),LocalDate.now());
    }

    @Override
    public List<Student> findStudentsWithBestSingleScoreByHouseId(long id) throws IdDoesNotExistException {
        Optional<House> opHouse = houseRepo.findById(id);
        if (opHouse.isEmpty()){
            throw new IdDoesNotExistException("house id not found");
        }
        return studentRepo.findByBestSingleScoreAndHouseId(id);
    }
    @Override
    public List<Student> findStudentsWithBestSingleScoreByHouseIdAndClassID(long houseId, long courseId) throws IdDoesNotExistException {
        Optional<Course> opClass = courseRepo.findById(courseId);
        if (opClass.isEmpty()){
            throw new IdDoesNotExistException("course id not found");
        }
        Optional<House> opHouse = houseRepo.findById(houseId);
        if (opHouse.isEmpty()){
            throw new IdDoesNotExistException("house id not found");
        }
        return studentRepo.findByBestSingleScoreAndHouseIdAndClassId(houseId, courseId);
    }

    @Override
    public List<Student> findBestStudentsByHouseId(long id) throws IdDoesNotExistException {
        Optional<House> opHouse = houseRepo.findById(id);
        if (opHouse.isEmpty()){
            throw new IdDoesNotExistException("house id not found");
        }
        return studentRepo.findBestFromHouse(id);
    }

    @Override
    public List<Student> findBestStudentsByHouseIdAndClassID(long houseId, long courseId) throws IdDoesNotExistException {
        Optional<House> opHouse = houseRepo.findById(houseId);
        if (opHouse.isEmpty()){
            throw new IdDoesNotExistException("house id not found");
        }
        Optional<Course> opClass = courseRepo.findById(courseId);
        if (opClass.isEmpty()){
            throw new IdDoesNotExistException("class id not found");
        }
        return studentRepo.findBestFromHouseAndClass(houseId, courseId);
    }

    @Override
    public List<Student> findStudentWithScoreReasonContains(String reason) throws ResourceNotFoundException {
        if (reason.isEmpty()) {
            throw new ResourceNotFoundException("reason is empty");
        }
        return studentRepo.findScoresByReason(reason);
    }

    @Override
    public Student createStudent(Student toSave, long houseId, long courseId) throws ResourceNotFoundException{
        Optional<House> opHouse = houseRepo.findById(houseId);
        if (opHouse.isEmpty()) {
            throw new ResourceNotFoundException("house id not found");
        }
        Optional<Course> opCourse = courseRepo.findById(courseId);
        if (opCourse.isEmpty()) {
            throw new ResourceNotFoundException("course not found");
        }
        toSave.setCourse(opCourse.get());
        toSave.setHouse(opHouse.get());
        return studentRepo.save(toSave);
    }
    @Override
    public void deleteStudent(long id) throws ResourceNotFoundException{
        Optional<Student> opStudent = studentRepo.findById(id);
        if (opStudent.isEmpty()) {
            throw new ResourceNotFoundException("student not found");
        }
        studentRepo.delete(opStudent.get());
    }

    @Override
    public void updateStudent(Student s, long houseId, long courseId) throws ResourceNotFoundException {
        System.out.println(courseId);
        System.out.println(houseId);
        Optional<House> optionalHouse = houseRepo.findById(houseId);
        if (optionalHouse.isEmpty()) {
            throw new ResourceNotFoundException("house not found");
        }
        Optional<Course> optionalCourse = courseRepo.findById(courseId);
        if (optionalCourse.isEmpty()) {
            throw new ResourceNotFoundException("class not found");
        }
        Optional<Student> opStudent = studentRepo.findById(s.getId());
        if (opStudent.isEmpty()) {
            throw new ResourceNotFoundException("student not found");
        }
        s.setHouse(optionalHouse.get());
        s.setCourse(optionalCourse.get());
        studentRepo.save(s);
    }
}
