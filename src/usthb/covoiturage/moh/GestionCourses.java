package usthb.covoiturage.moh;

import java.util.*;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


public class GestionCourses {
    private final Map<String, Course> courses;
    private final Map<String, List<Course>> coursesParChauffeur;

    public GestionCourses() {
        this.courses = new HashMap<>();
        this.coursesParChauffeur = new HashMap<>();
    }

    public void ajouterCourse(Course course) {
        // 1. Null checks
        if (course == null) {
            throw new IllegalArgumentException("Course cannot be null");
        }
        if (course.getChauffeur() == null) {
            throw new IllegalArgumentException("Course driver cannot be null");
        }

        // 2. Add to main courses map
        courses.put(course.getId(), course);

        // 3. Add to driver-specific map
        String matricule = course.getChauffeur().getMatricule();
        coursesParChauffeur
                .computeIfAbsent(matricule, k -> new ArrayList<>())
                .add(course);
    }

    /**
     * Returns an unmodifiable collection of available rides
     * (scheduled rides with available seats)
     */
    public Collection<Course> getCoursesDisponibles() {
        return courses.values().stream()
                .filter(c -> c.getStatut() == Course.StatutCourse.PLANNIFIEE)
                .filter(c -> c.getPlacesDisponibles() > 0)
                .collect(Collectors.toUnmodifiableList());
    }

    /**
     * Returns an unmodifiable collection of ongoing rides
     */
    public Collection<Course> getCoursesEnCours() {
        return courses.values().stream()
                .filter(c -> c.getStatut() == Course.StatutCourse.EN_COURS)
                .collect(Collectors.toUnmodifiableList());
    }

    /**
     * Returns an unmodifiable collection of completed rides
     */
    public Collection<Course> getCoursesTerminees() {
        return courses.values().stream()
                .filter(c -> c.getStatut() == Course.StatutCourse.TERMINEE)
                .collect(Collectors.toUnmodifiableList());
    }
}

