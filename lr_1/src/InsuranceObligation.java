import java.io.Serializable;
import java.util.Objects;

public class InsuranceObligation implements Serializable {
    private String title;
    private int dollarsPerYear;
    private int riskPercent;
    private int ID;



    public InsuranceObligation(String title, int dollarsPerYear, int riskPercent){
        this.dollarsPerYear=dollarsPerYear;
        this.riskPercent =riskPercent;
        this.title=title;
    }

    public int getID() { return ID; }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public int getRiskPercent() {
        return riskPercent;
    }

    public int getDollarsPerYear() {
        return dollarsPerYear;
    }

    public void setDollarsPerYear(int dollarsPerYear) {
        this.dollarsPerYear = dollarsPerYear;
    }

    public void setRiskPercent(int riskPercent) {
        this.riskPercent = riskPercent;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "InsuranceObligation{" +
                "title='" + title + '\'' +
                ", dollarsPerYear=" + dollarsPerYear +
                ", riskProcent=" + riskPercent +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InsuranceObligation that = (InsuranceObligation) o;
        return dollarsPerYear == that.dollarsPerYear && riskPercent == that.riskPercent && Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, dollarsPerYear, riskPercent);
    }
}
