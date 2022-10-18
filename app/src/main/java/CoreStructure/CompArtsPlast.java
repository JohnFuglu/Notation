package CoreStructure;

public class CompArtsPlast {

    private static final String C1 = "Expérimenter, produire, créer";
    private static final String C1c1 = "Choisir, organiser et mobiliser des gestes, des outils et des matériaux en fonction des effets qu’ils produisent.";
    private static final String C1c2 = "Représenter le monde environnant ou donner forme à son imaginaire en explorant divers domaines (dessin, collage, modelage, sculpture, photographie, vidéo...).";
    private static final String C1c3 = "Rechercher une expression personnelle en s’éloignant des stéréotypes.";
    private static final String C1c4 = "Intégrer l’usage des outils informatique de travail de l’image et de recherche d’information, au service de la pratique plastique.";

    private static final String C2 = "Mettre en oeuvre un projet artistique.";
    //TODO les sous compétences
    private static final String C3 = "S'exprimer, analyser sa pratique, celle de ses pairs; établir une relation avec celle des artistes, s'ouvrir à l'altérité.";
    private static final String C4 = "Se repérer dans les domaines liés aux Arts Plastiques, être sensible aux questions de l'Art.";

    public static String[] getCompetences() {
        //   TODO maj ac le reste des comp
        String[] toReturn = {"Aucune", C1, C1c1, C1c2, C1c3, C1c4, C2, C3, C4};
        return toReturn;
    }
}
