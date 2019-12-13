package com.xmu.wowomall.footprint.domain;

import com.xmu.wowomall.footprint.domain.Po.CollectItemPo;
import com.xmu.wowomall.footprint.domain.Po.GoodsPo;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author: 数据库与对象模型标准组
 * @Description:收藏夹明细对象
 * @Data:Created in 14:50 2019/12/11
 **/
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class CollectItem extends CollectItemPo {

    private GoodsPo goodsPo;
}
