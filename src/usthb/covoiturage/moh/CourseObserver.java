package usthb.covoiturage.moh;

import java.util.Observable;
public class CourseObserver implements java.util.Observer {
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("[Notification] " + arg);
        if (o instanceof Course) {
            Course course = (Course) o;
            System.out.println("Statut actuel de la course " + course.getId() + ": " + course.getStatut());
        }
    }
}
