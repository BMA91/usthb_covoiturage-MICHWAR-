package usthb.covoiturage.moh;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

    public class Course extends Observable {
        // Enum for ride type
        public enum TypeCourse {
            ALLER_SIMPLE,
            ALLER_RETOUR,
            RETOUR_SIMPLE
        }

        // Enum for ride status
        public enum StatutCourse {
            PLANNIFIEE,
            EN_COURS,
            TERMINEE,
            ANNULEE
        }

        private String id;
        private Utilisateur chauffeur;
        private List<Utilisateur> passagers;
        private TypeCourse type;
        private StatutCourse statut;
        private Itineraire itineraire;
        private int capaciteMax;
        private LocalDateTime dateCreation;

        public Course(String id, Utilisateur chauffeur, TypeCourse type, Itineraire itineraire, int capaciteMax) {
            this.id = id;
            this.chauffeur = chauffeur;
            this.type = type;
            this.itineraire = itineraire;
            this.capaciteMax = capaciteMax;
            this.passagers = new ArrayList<>();
            this.statut = StatutCourse.PLANNIFIEE;
            this.dateCreation = LocalDateTime.now();
        }

        public void ajouterPassager(Utilisateur passager) throws CapaciteDepasseeException {
            if (passagers.size() >= capaciteMax) {
                throw new CapaciteDepasseeException("La capacité maximale de " + capaciteMax + " passagers est atteinte.");
            }
            passagers.add(passager);
            setChanged();
            notifyObservers("Passager ajouté: " + passager.getNomComplet());
        }

        public void demarrerCourse() {
            this.statut = StatutCourse.EN_COURS;
            setChanged();
            notifyObservers("Course démarrée");
        }

        public void terminerCourse() {
            this.statut = StatutCourse.TERMINEE;
            setChanged();
            notifyObservers("Course terminée");
        }

        public void annulerCourse() {
            this.statut = StatutCourse.ANNULEE;
            setChanged();
            notifyObservers("Course annulée");
        }

        // Getters
        public String getId() { return id; }
        public Utilisateur getChauffeur() { return chauffeur; }
        public List<Utilisateur> getPassagers() { return new ArrayList<>(passagers); }
        public TypeCourse getType() { return type; }
        public StatutCourse getStatut() { return statut; }
        public Itineraire getItineraire() { return itineraire; }
        public int getCapaciteMax() { return capaciteMax; }
        public int getPlacesDisponibles() { return capaciteMax - passagers.size(); }
        public LocalDateTime getDateCreation() { return dateCreation; }

        // Custom exception for capacity overflow
        public static class CapaciteDepasseeException extends Exception {
            public CapaciteDepasseeException(String message) {
                super(message);
            }
        }
    }


