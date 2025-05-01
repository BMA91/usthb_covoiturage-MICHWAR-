package usthb.covoiturage.moh;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Itineraire {


        private List<String> pointsPassage;
        private LocalDateTime heureDepart;
        private LocalDateTime heureArrivee;

        public Itineraire(List<String> pointsPassage, LocalDateTime heureDepart, LocalDateTime heureArrivee) {
            Objects.requireNonNull(pointsPassage, "La liste des points de passage ne peut pas être nulle");
            Objects.requireNonNull(heureDepart, "L'heure de départ ne peut pas être nulle");
            Objects.requireNonNull(heureArrivee, "L'heure d'arrivée ne peut pas être nulle");

            if (pointsPassage.isEmpty()) {
                throw new IllegalArgumentException("L'itinéraire doit contenir au moins un point de passage");
            }
            if (heureArrivee.isBefore(heureDepart)) {
                throw new IllegalArgumentException("L'heure d'arrivée doit être après l'heure de départ");
            }

            this.pointsPassage = new ArrayList<>(pointsPassage);
            this.heureDepart = heureDepart;
            this.heureArrivee = heureArrivee;
        }

        public long calculerDureeMinutes() {
            return java.time.Duration.between(heureDepart, heureArrivee).toMinutes();
        }

        public boolean contientPoint(String point) {
            return pointsPassage.contains(point);
        }

        public boolean estCompatibleAvec(Itineraire autre) {
            // Vérifie si les itinéraires ont des points en commun
            return pointsPassage.stream().anyMatch(autre::contientPoint);
        }

        // Getters
        public List<String> getPointsPassage() { return new ArrayList<>(pointsPassage); }
        public LocalDateTime getHeureDepart() { return heureDepart; }
        public LocalDateTime getHeureArrivee() { return heureArrivee; }

        @Override
        public String toString() {
            return "Itineraire{" +
                    "pointsPassage=" + pointsPassage +
                    ", heureDepart=" + heureDepart +
                    ", heureArrivee=" + heureArrivee +
                    '}';
        }
    }

