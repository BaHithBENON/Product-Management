package enums;

public enum Category {
	BISCUIT("Buiscuit"),
    BONBON("Bonbon");

    private String category;

    private Category(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }
}
