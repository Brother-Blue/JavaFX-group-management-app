package member_manager;

public class RiskMatrix {
    private String riskName;
    private String veryLikely;
    private String possible;
    private String unlikely;

    public RiskMatrix(String riskName, String veryLikely, String possible, String unlikely){
        this.riskName = riskName;
        this.veryLikely = veryLikely;
        this.possible = possible;
        this.unlikely = unlikely;
    }

    public String getRiskName() {  return riskName; }

    public void setRiskName(String riskName) { this.riskName = riskName; }

    public String getVeryLikely() { return veryLikely; }

    public void setVeryLikely(String veryLikely) { this.veryLikely = veryLikely; }

    public String getPossible() { return possible; }

    public void setPossible(String possible) { this.possible = possible; }

    public String getUnlikely() { return unlikely; }

    public void setUnlikely(String unlikely) { this.unlikely = unlikely; }

}
