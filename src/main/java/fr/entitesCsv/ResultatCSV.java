package fr.entitesCsv;

import fr.entitesData.ResultatData;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe qui stock une list de type ResultatData du fichier CSV results.csv
 */
public class ResultatCSV {

    private List<ResultatData> resultatData = new ArrayList<>();

    /**
     * @return une liste de resultatData
     */
    public List<ResultatData> getResultatData() {
        return resultatData;
    }

    /**
     * @param resultatData la nouvelle list de butData
     */
    public void setResultatData(List<ResultatData> resultatData) {
        this.resultatData = resultatData;
    }

    @Override
    public String toString() {
        return "ResultatCSV{" +
                "resultatData=" + resultatData +
                '}';
    }
}
