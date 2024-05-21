package com.kimhong.project_final.data.model.productdetail;

public class ProductDetailResponse {
    private int code;
    private ProductDetailResult result;

    public ProductDetailResponse(int code, ProductDetailResult result) {
        this.code = code;
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ProductDetailResult getResult() {
        return result;
    }

    public void setResult(ProductDetailResult result) {
        this.result = result;
    }
}
