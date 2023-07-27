package cash.vo;

public class Category {
	private int categoryNo;
	private String category;
	private String subcategory;
	
	public Category(int categoryNo, String category, String subcategory) {
		super();
		this.categoryNo = categoryNo;
		this.category = category;
		this.subcategory = subcategory;
	}

	@Override
	public String toString() {
		return "Category [categoryNo=" + categoryNo + ", category=" + category + ", subcategory=" + subcategory + "]";
	}

	public int getCategoryNo() {
		return categoryNo;
	}

	public void setCategoryNo(int categoryNo) {
		this.categoryNo = categoryNo;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(String subcategory) {
		this.subcategory = subcategory;
	}
	
	
}
