package com.pillll.pillll.database;


import com.pillll.pillll.database.entity.Asmr;
import com.pillll.pillll.database.entity.Composition;
import com.pillll.pillll.database.entity.ConditionPrescription;
import com.pillll.pillll.database.entity.Generique;
import com.pillll.pillll.database.entity.LienCt;
import com.pillll.pillll.database.entity.Presentation;
import com.pillll.pillll.database.entity.Smr;
import com.pillll.pillll.database.entity.Specialite;
import com.pillll.pillll.database.entity.TitulaireSpecialite;
import com.pillll.pillll.database.entity.VoiesAdministration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Generates data to pre-populate the Pillll database
 */

public class DataGenerator {

    //DATA
    // SPECIALITE DATA
    private static final long[] ID_CODE_CIS = new long[]{60002283, 60002504, 60002746, 60003620, 60004277, 60004487};
    private static final String[] DENOMINATION = new String[]{"ANASTROZOLE ACCORD 1 mg, comprimé pelliculé", "RANITIDINE BIOGARAN 150 mg, comprimé effervescent", "ACTAEA RACEMOSA FERRIER, degré de dilution compris entre 2CH et 30CH ou entre 4DH et 60DH", "BECLOSPIN 800 microgrammes/2ml suspension pour inhalation par nébuliseur", "FENOFIBRATE TEVA 100 mg, gélule", "TRAMADOL EG L.P. 200 mg, comprimé à libération prolongée"};
    private static final String[] FORME_PHARMACEUTIQUE = new String[]{"comprimé pelliculé", "comprimé effervescent(e)", "granules et  solution en gouttes en gouttes", "suspension pour inhalation par nébuliseur", "gélule", "comprimé à libération prolongée"};
    // PRESENTATION DATA
    private static final String[] CODE_CIP7 = new String[]{"4949729", "3320863", "3320863", "3696350", "3614582", "3972519"};
    private static final String[] CODE_CIP13 = new String[]{"3400949497294", "3400933208639", "3400933208639", "3400936963504", "3400936145825", "3400939725192"};
    // COMPOSITION DATA
    private static final String[] DENOMINATION_SUBSTANCE = new String[]{"ANASTROZOLE", "RANITIDINE BASE", "ACTAEA RACEMOSA POUR PRÉPARATIONS HOMÉOPATHIQUES", "DIPROPIONATE DE BÉCLOMÉTASONE", "FÉNOFIBRATE", "TRAMADOL"};
    // SMR DATA
    private static final String[] MOTIF_EVALUATION = new String[]{"Réévaluation SMR", "Réévaluation SMR", "Renouvellement d'inscription (CT)", "Renouvellement d'inscription (CT)"};
    private static final String[] LIEN_CT_CODE_DOSSIER_HAS = new String[]{"CT-11300", "CT-11300", "CT-15298", "CT-15298"};
    private static final String[] VALEUR = new String[]{"Faible", "Insuffisant", "Important", "Important" };
    // LIEN_AVIS_CT DATA
    private static final String[] LIEN_AVIS_CT = new String[]{"https://www.has-sante.fr/portail/jcms/c_1146496", "https://www.has-sante.fr/portail/jcms/c_1146496", "https://www.has-sante.fr/portail/jcms/c_2723186", "https://www.has-sante.fr/portail/jcms/c_2723186" };
    // CONDITION_PRESCRIPTION DATA
    private static final String[] CONDITION_PRESCRIPTION = new String[]{"liste I", "liste II", "Liste II", "liste I", "liste II", "liste I"};
    // GENERIQUE DATA
    private static final String[] IDENTIFIANT_GROUPE = new String[]{"659", "10", "", "1358", "104", "700"};
    private static final String[] LIBELLE_GROUPE = new String[]{"ANASTROZOLE 1 mg - ARIMIDEX 1 mg, comprimé pelliculé.", "RANITIDINE (CHLORHYDRATE DE) équivalant à RANITIDINE 150 mg - AZANTAC 150 mg, comprimé effervescent - RANIPLEX 150 mg, comprimé effervescent.", "", "BECLOMETASONE (DIPROPIONATE DE) 800 microgrammes/2 mL - BECLOSPIN 800 microgrammes/2 ml, suspension pour inhalation par nébuliseur", "FENOFIBRATE 100 mg - FENOX 100 mg, gélule", "TRAMADOL (CHLORHYDRATE DE) 200 mg - CONTRAMAL L.P. 200 mg, comprimé pelliculé à libération prolongée - TOPALGIC L.P. 200 mg, comprimé à libération prolongée."};
    // TITULAIRE_SPECIALITE DATA
    private static final String[] TITULAIRE = new String[]{"ACCORD HEALTHCARE FRANCE", "SAINT GERMAIN", "FERRIER", "CHIESI", "TEVA SANTE", "EG LABO - LABORATOIRES EUROGENERICS"};
    // VOIES_ADMINISTRATION DATA
    private static final String[] VOIES_ADMINISTRATION = new String[]{"orale", "orale", "orale;sublinguale", "inhalée", "orale", "orale"};

    public static List<Specialite> generateSpecialites() {
        List<Specialite> specialites = new ArrayList<>(ID_CODE_CIS.length);
        for (int i = 0; i < ID_CODE_CIS.length; i++) {
            Specialite specialite = new Specialite();
            specialite.setDateAmm(new Date(1985, 10, 22));
            specialite.setDenomination(DENOMINATION[i]);
            specialite.setEtatCommercialisation("Commercialisée");
            specialite.setFormePharmaceutique(FORME_PHARMACEUTIQUE[i]);
            specialite.setIdCodeCis(ID_CODE_CIS[i]);
            specialite.setNumeroAutorisationEuro("");
            specialite.setStatutAdministratifAmm("Autorisation active");
            specialite.setStatutBdm("");
            specialite.setSurveillanceRenforcee(false);
            specialite.setTypeProcedureAmm("Procédure de reconnaissance mutuelle");
            specialites.add(specialite);
        }
        return specialites;
    }

    public static List<Presentation> generatePresentations() {
        List<Presentation> presentations = new ArrayList<>(ID_CODE_CIS.length);
        Random random = new Random();
        for (int i = 0; i < ID_CODE_CIS.length; i++) {
            Presentation presentation = new Presentation();
            presentation.setCodeCip7(CODE_CIP7[i]);
            presentation.setCodeCip13(CODE_CIP13[i]);
            presentation.setAgrementCollectivites(true);
            presentation.setDateCommercialisation(new Date(1988, 22, 10));
            presentation.setEtatCommercialisation("Déclaration de commercialisation");
            presentation.setId(i);
            presentation.setLibelle("plaquette(s) opaque(s) PVC-Aluminium de 30 comprimé(s)");
            presentation.setPrecisionRemboursement("");
            presentation.setPrixEuros(random.nextInt(250));
            presentation.setSpecialiteIdCodeCis(ID_CODE_CIS[i]);
            presentation.setTauxRemboursement(100);
            presentation.setStatutAdministratif("Présentation active");
            presentations.add(presentation);
        }
        return presentations;
    }

    public static List<Composition> generateCompositions() {
        List<Composition> compositions = new ArrayList<>(DENOMINATION_SUBSTANCE.length);
        for (int i = 0; i < DENOMINATION_SUBSTANCE.length; i ++){
            Composition composition = new Composition();
            composition.setId(i+1);
            composition.setCodeSubstance("35415");
            composition.setDenominationSubstance(DENOMINATION_SUBSTANCE[i]);
            composition.setSpecialiteIdCodeCis(ID_CODE_CIS[i]);
            composition.setDosageSubstance("200 mg");
            composition.setElementPharmaceutique("comprimé");
            composition.setNumeroLiaison(1);
            composition.setNatureComposant("SA");
            composition.setReferenceDosage("un comprimé");
            compositions.add(composition);
        }
        return compositions;
    }

    public static List<Asmr> generateAsmr(){
        List<Asmr> asmrs = new ArrayList<>(1);
        Asmr asmr = new Asmr();
        asmr.setId(5929);
        asmr.setDateAvisCt(null);
        asmr.setMotifEvaluation("Inscription (CT)");
        asmr.setValeur("V");
        asmr.setLibelle("BECLOSPIN 400 µg/1 ml et 800 µg/2 ml n\u0092apporte pas d\u0092amélioration du service médical rendu (niveau V) par rapport à PULMICORT 0,5 mg/2 ml et 1 mg/2 ml.");
        asmr.setLienCtCodeDossierHas("CT-2582");
        asmr.setSpecialiteIdCodeCis(60003620);
        asmrs.add(asmr);
        return asmrs;
    }

    public static List<Smr> generateSmr(){
        List<Smr> smrs = new ArrayList<>(MOTIF_EVALUATION.length);
        for (int i = 0; i < MOTIF_EVALUATION.length; i ++){
            Smr smr = new Smr();
            smr.setId(i+1);
            smr.setDateAvisCt(new Date(2012, 10, 10));
            smr.setLibelle("Le service médical rendu par BECLOSPIN reste important dans l\u0092indication de l\u0092AMM.");
            smr.setLienCtCodeDossierHas(LIEN_CT_CODE_DOSSIER_HAS[i]);
            smr.setMotifEvaluation(MOTIF_EVALUATION[i]);
            smr.setSpecialiteIdCodeCis(ID_CODE_CIS[i]);
            smr.setValeur(VALEUR[i]);
            smrs.add(smr);
        }
        return smrs;
    }

    public static List<LienCt> generateLienCt(){
        List<LienCt> lienCts = new ArrayList<>(LIEN_AVIS_CT.length);
        for (int i = 0; i < LIEN_AVIS_CT.length; i++){
            LienCt lienCt = new LienCt();
            lienCt.setCodeDossierHas(LIEN_CT_CODE_DOSSIER_HAS[i]);
            lienCt.setLienAvisCt(LIEN_AVIS_CT[i]);
            lienCts.add(lienCt);
        }
        return lienCts;
    }

    public static List<ConditionPrescription> generateConditionPrescriptions(){

        List<ConditionPrescription> conditionPrescriptions = new ArrayList<>(CONDITION_PRESCRIPTION.length);
        for (int i = 0; i < CONDITION_PRESCRIPTION.length; i ++){
            ConditionPrescription conditionPrescription = new ConditionPrescription();
            conditionPrescription.setId(i+1);
            conditionPrescription.setSpecialiteIdCodeCis(ID_CODE_CIS[i]);
            conditionPrescription.setConditionPrescription(CONDITION_PRESCRIPTION[i]);
            conditionPrescriptions.add(conditionPrescription);
        }
        return conditionPrescriptions;
    }

    public static List<Generique> generateGeneriques(){
        List<Generique> generiques = new ArrayList<>(ID_CODE_CIS.length);
        for (int i = 0; i < ID_CODE_CIS.length; i++){
            Generique generique = new Generique();
            generique.setIdentifiantGroupe(IDENTIFIANT_GROUPE[i]);
            generique.setSpecialiteIdCodeCis(ID_CODE_CIS[i]);
            generique.setLibelleGroupe(LIBELLE_GROUPE[i]);
            generique.setNumeroTri("4");
            generique.setType("7");
            generiques.add(generique);
        }
        return generiques;
    }

    public static List<TitulaireSpecialite> generateTitulaireSpecialites(){
        List<TitulaireSpecialite> titulaireSpecialites = new ArrayList<>(ID_CODE_CIS.length);
        for (int i = 0; i < ID_CODE_CIS.length; i++){
            TitulaireSpecialite titulaireSpecialite = new TitulaireSpecialite();
            titulaireSpecialite.setId(i+1);
            titulaireSpecialite.setSpecialiteIdCodeCis(ID_CODE_CIS[i]);
            titulaireSpecialite.setTitulaire(TITULAIRE[i]);
            titulaireSpecialites.add(titulaireSpecialite);
        }
        return titulaireSpecialites;
    }

    public static List<VoiesAdministration> generateVoiesAdministrations(){
        List<VoiesAdministration> voiesAdministrations = new ArrayList<>(ID_CODE_CIS.length);
        for (int i = 0; i < ID_CODE_CIS.length; i++){
            VoiesAdministration voiesAdministration = new VoiesAdministration();
            voiesAdministration.setId(i+1);
            voiesAdministration.setSpecialiteIdCodeCis(ID_CODE_CIS[i]);
            voiesAdministration.setVoiesAdministration(VOIES_ADMINISTRATION[i]);
            voiesAdministrations.add(voiesAdministration);
        }
        return voiesAdministrations;
    }
}
