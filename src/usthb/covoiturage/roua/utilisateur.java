package usthb.covoiturage.roua;

  public abstract class Utilisateur {
        // Common attributes (encapsulated)
        private String nom;
        private String prenom;
        private String matricule;
        private float reputation = 3.0f; // Default
        private StatutUtilisateur statut; // Enum: PASSAGER, CHAUFFEUR
        private Set<String> preferences = new HashSet<>(); // e.g., "NO_MUSIC", "FEMALE_ONLY"
    
        // Constructor
        public Utilisateur(String nom, String prenom, String matricule) {
            this.nom = nom;
            this.prenom = prenom;
            this.matricule = matricule;
        }
    
        // Reputation update (shared with Amine's system)
        // public void mettreAJourReputation(float nouvelleNote) {
        //     this.reputation = (this.reputation * nombreEvaluations + nouvelleNote) / (nombreEvaluations + 1);
        //     if (this.reputation < 2.0f) {
        //         AdminSystem.alertBannissement(this.matricule); 
        //     }
        // }
    
        // Getters/Setters (omitted for brevity)
    }
