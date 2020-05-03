package cn.shiying.drugs_purchase.service.impl;

import cn.shiying.common.entity.Drugs.DrugsDetailed;
import cn.shiying.common.entity.Drugs.DrugsPurchaseDetailed;
import cn.shiying.common.entity.supplier.SupplierDetailed;
import cn.shiying.drugs_purchase.config.DrugsSchedule;
import cn.shiying.drugs_purchase.entity.form.Drugs;
import cn.shiying.drugs_purchase.entity.form.DrugsAndDetailed;
import cn.shiying.drugs_purchase.entity.DrugsPurchase;
import cn.shiying.drugs_purchase.entity.vo.DrugsPurchaseDetailedVO;
import cn.shiying.drugs_purchase.entity.vo.DrugsSupplierVO;
import cn.shiying.drugs_purchase.entity.vo.PurchaseSupplierVo;
import cn.shiying.drugs_purchase.mapper.DrugsPurchaseMapper;
import cn.shiying.drugs_purchase.service.DrugsPurchaseService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.shiying.common.utils.Query;
import cn.shiying.common.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tyb
 * @since 2020-04-22
 */
@Service
public class DrugsPurchaseServiceImpl extends ServiceImpl<DrugsPurchaseMapper, DrugsPurchase> implements DrugsPurchaseService {

    @Autowired
    DrugsSchedule schedule;

    /**
     * 分页查询
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page page=new Query<DrugsPurchase>(params).getPage();
        List<PurchaseSupplierVo> list= baseMapper.DrugsPurchaseList(page,params);
        page.setRecords(list);
        return new PageUtils(page);
    }

    public List<DrugsPurchaseDetailedVO> selectBypid(String pid) { return baseMapper.selectBypid(pid); }
    public List<SupplierDetailed> selectSname() { return baseMapper.selectSname(); }
    public List<DrugsDetailed> selectDname(){ return baseMapper.selectDname(); }
    public int insertPurchase(DrugsPurchase drugsPurchase){ return baseMapper.insertPurchase(drugsPurchase); }
    public int insertDetailed(DrugsPurchaseDetailed drugsPurchaseDetailed){ return baseMapper.insertDetailed(drugsPurchaseDetailed); }

    @Override
    public void addSupplierAndDrugs(DrugsAndDetailed drugsAndDetailed) {
        //进货单号
        String PurchaseId = schedule.createId();

        //循环Drugs数据
        List<Drugs> DrugsList=drugsAndDetailed.getDetailed();
        Double AllTotal=0.0;
        for (Drugs drugs : DrugsList) {
            Integer price= drugs.getPrice();
            Integer drugsNum = drugs.getNum();
            Integer total = price*drugsNum;
            AllTotal+=total;
        }

        //药品购入表
        DrugsPurchase dp=new DrugsPurchase();
        dp.setPurchaseId(PurchaseId);//订单编号
        dp.setSupplierId(drugsAndDetailed.getSupplierId());//供应商编号
        dp.setPurchaseAmountPayable(AllTotal);//应付金额
        dp.setPurchaseActualAmountPaid(drugsAndDetailed.getPayPrice());//已付订金
        baseMapper.addDrugsPurchase(dp);

        //药品购入详细表
        List<DrugsPurchaseDetailed> as=new ArrayList<>();
        DrugsPurchaseDetailed dpd;
        //循环Drugs数据
        List<Drugs> list=drugsAndDetailed.getDetailed();
        for (Drugs drugs : list) {
            dpd=new DrugsPurchaseDetailed();
            dpd.setDrugsId(drugs.getDrugsId());//药品编号
            dpd.setPurchaseId(dp.getPurchaseId());//药品购入表编号
            dpd.setPdMoney(drugs.getPrice());//价格
            dpd.setPdNum(drugs.getNum());//数量
            as.add(dpd);
        }
        baseMapper.addDrugsPurchaseDetailed(as);
    }

}
