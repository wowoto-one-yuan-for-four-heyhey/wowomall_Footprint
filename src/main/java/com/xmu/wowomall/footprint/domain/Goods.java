package com.xmu.wowomall.footprint.domain;

import com.xmu.wowomall.footprint.domain.Po.BrandPo;
import com.xmu.wowomall.footprint.domain.Po.GoodsCategoryPo;
import com.xmu.wowomall.footprint.domain.Po.GoodsPo;
import com.xmu.wowomall.footprint.domain.Po.ProductPo;

import java.util.List;

/**
 * @Author: 数据库与对象模型标准组
 * @Description:商品对象
 * @Data:Created in 14:50 2019/12/11
 **/
public class Goods extends GoodsPo {
    private BrandPo brandPo;
    private GoodsCategoryPo goodsCategoryPo;
    private List<ProductPo> productPoList;
    private GrouponRule grouponRule;
    private ShareRule shareRule;
    private PresaleRule presaleRule;

    public BrandPo getBrandPo() {
        return this.brandPo;
    }

    public GoodsCategoryPo getGoodsCategoryPo() {
        return this.goodsCategoryPo;
    }

    public List<ProductPo> getProductPoList() {
        return this.productPoList;
    }

    public GrouponRule getGrouponRule() {
        return this.grouponRule;
    }

    public ShareRule getShareRule() {
        return this.shareRule;
    }

    public PresaleRule getPresaleRule() {
        return this.presaleRule;
    }

    public void setBrandPo(BrandPo brandPo) {
        this.brandPo = brandPo;
    }

    public void setGoodsCategoryPo(GoodsCategoryPo goodsCategoryPo) {
        this.goodsCategoryPo = goodsCategoryPo;
    }

    public void setProductPoList(List<ProductPo> productPoList) {
        this.productPoList = productPoList;
    }

    public void setGrouponRule(GrouponRule grouponRule) {
        this.grouponRule = grouponRule;
    }

    public void setShareRule(ShareRule shareRule) {
        this.shareRule = shareRule;
    }

    public void setPresaleRule(PresaleRule presaleRule) {
        this.presaleRule = presaleRule;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Goods)) return false;
        final Goods other = (Goods) o;
        if (!other.canEqual((Object) this)) return false;
        if (!super.equals(o)) return false;
        final Object this$brandPo = this.getBrandPo();
        final Object other$brandPo = other.getBrandPo();
        if (this$brandPo == null ? other$brandPo != null : !this$brandPo.equals(other$brandPo)) return false;
        final Object this$goodsCategoryPo = this.getGoodsCategoryPo();
        final Object other$goodsCategoryPo = other.getGoodsCategoryPo();
        if (this$goodsCategoryPo == null ? other$goodsCategoryPo != null : !this$goodsCategoryPo.equals(other$goodsCategoryPo))
            return false;
        final Object this$productPoList = this.getProductPoList();
        final Object other$productPoList = other.getProductPoList();
        if (this$productPoList == null ? other$productPoList != null : !this$productPoList.equals(other$productPoList))
            return false;
        final Object this$grouponRule = this.getGrouponRule();
        final Object other$grouponRule = other.getGrouponRule();
        if (this$grouponRule == null ? other$grouponRule != null : !this$grouponRule.equals(other$grouponRule))
            return false;
        final Object this$shareRule = this.getShareRule();
        final Object other$shareRule = other.getShareRule();
        if (this$shareRule == null ? other$shareRule != null : !this$shareRule.equals(other$shareRule)) return false;
        final Object this$presaleRule = this.getPresaleRule();
        final Object other$presaleRule = other.getPresaleRule();
        if (this$presaleRule == null ? other$presaleRule != null : !this$presaleRule.equals(other$presaleRule))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Goods;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = super.hashCode();
        final Object $brandPo = this.getBrandPo();
        result = result * PRIME + ($brandPo == null ? 43 : $brandPo.hashCode());
        final Object $goodsCategoryPo = this.getGoodsCategoryPo();
        result = result * PRIME + ($goodsCategoryPo == null ? 43 : $goodsCategoryPo.hashCode());
        final Object $productPoList = this.getProductPoList();
        result = result * PRIME + ($productPoList == null ? 43 : $productPoList.hashCode());
        final Object $grouponRule = this.getGrouponRule();
        result = result * PRIME + ($grouponRule == null ? 43 : $grouponRule.hashCode());
        final Object $shareRule = this.getShareRule();
        result = result * PRIME + ($shareRule == null ? 43 : $shareRule.hashCode());
        final Object $presaleRule = this.getPresaleRule();
        result = result * PRIME + ($presaleRule == null ? 43 : $presaleRule.hashCode());
        return result;
    }

    public String toString() {
        return "Goods(brandPo=" + this.getBrandPo() + ", goodsCategoryPo=" + this.getGoodsCategoryPo() + ", productPoList=" + this.getProductPoList() + ", grouponRule=" + this.getGrouponRule() + ", shareRule=" + this.getShareRule() + ", presaleRule=" + this.getPresaleRule() + ")";
    }
}
