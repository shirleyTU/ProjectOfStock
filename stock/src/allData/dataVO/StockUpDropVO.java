package allData.dataVO;

public class StockUpDropVO {
	private String id;   //编码
	private String stockTimeId;  //main表中的时间
	private String stockCode;  //股票代码
	private String stockName;  //股票名称
	private String stockUpType;  //股票涨停/跌停类型
	private double currentPrice;  //现价
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStockTimeId() {
		return stockTimeId;
	}
	public void setStockTimeId(String stockTimeId) {
		this.stockTimeId = stockTimeId;
	}
	public String getStockCode() {
		return stockCode;
	}
	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}
	public String getStockName() {
		return stockName;
	}
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	public String getStockUpType() {
		return stockUpType;
	}
	public void setStockUpType(String stockUpType) {
		this.stockUpType = stockUpType;
	}
	public double getCurrentPrice() {
		return currentPrice;
	}
	public void setCurrentPrice(double currentPrice) {
		this.currentPrice = currentPrice;
	}
}
