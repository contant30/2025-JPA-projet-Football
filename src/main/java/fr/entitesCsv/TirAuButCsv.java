package fr.entitesCsv;

import fr.entitesData.TirAuButData;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe qui stock une list de type TirAuButData du fichier CSV shootouts.csv
 */
public class TirAuButCsv {

    /*
     * Une liste d'objet du fichier csv
     */
    private List<TirAuButData> tirAuButData = new ArrayList<>();

    /**
     * @return une liste de tirAuButData
     */
    public List<TirAuButData> getTirAuButData() {
        return tirAuButData;
    }

    /**
     * @param tirAuButData la nouvelle list de butData
     */
    public void setTirAuButData(List<TirAuButData> tirAuButData) {
        this.tirAuButData = tirAuButData;
    }

    @Override
    public String toString() {
        return "TirAuButCsv{" +
                "tirAuButData=" + tirAuButData +
                '}';
    }
}
