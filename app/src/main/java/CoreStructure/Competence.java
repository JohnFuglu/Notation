package CoreStructure;

/**CoreStructure.Classe de repr�sentant les comp�tences d'arts pla
 * validation de 1 � 4 (1 pour rouge => 4 pour vert +)*/
public class Competence {
    private final CompetencesArtsPla intitule;
    private Couleur validation;
    public Competence(CompetencesArtsPla intitule) {
        this.intitule=intitule;
    }
    public void evalueCompetence(Couleur eval) {this.validation=eval;}
}