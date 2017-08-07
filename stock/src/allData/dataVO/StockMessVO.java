package allData.dataVO;

public class StockMessVO {
	private String id;       //编码
	private String stockTime;          //股票数据时间
	private int stockRiseNumber;    //涨停数目
	private int stockDropNumber;   //跌停数目
	private int stockAllNumber;    //所有股票数目
	private int stockStopNumber;   //停牌数目
	private double stockEarningEffect;  //大盘赚钱效应
	private double stockSealingPlate;  //大盘封板率
	public double getStockSealingPlate() {
		return stockSealingPlate;
	}
	public void setStockSealingPlate(double stockSealingPlate) {
		this.stockSealingPlate = stockSealingPlate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStockTime() {
		return stockTime;
	}
	public void setStockTime(String stockTime) {
		this.stockTime = stockTime;
	}
	public int getStockRiseNumber() {
		return stockRiseNumber;
	}
	public void setStockRiseNumber(int stockRiseNumber) {
		this.stockRiseNumber = stockRiseNumber;
	}
	public int getStockDropNumber() {
		return stockDropNumber;
	}
	public void setStockDropNumber(int stockDropNumber) {
		this.stockDropNumber = stockDropNumber;
	}
	public int getStockAllNumber() {
		return stockAllNumber;
	}
	public void setStockAllNumber(int stockAllNumber) {
		this.stockAllNumber = stockAllNumber;
	}
	public int getStockStopNumber() {
		return stockStopNumber;
	}
	public void setStockStopNumber(int stockStopNumber) {
		this.stockStopNumber = stockStopNumber;
	}
	public double getStockEarningEffect() {
		return stockEarningEffect;
	}
	public void setStockEarningEffect(double stockEarningEffect) {
		this.stockEarningEffect = stockEarningEffect;
	}

}
