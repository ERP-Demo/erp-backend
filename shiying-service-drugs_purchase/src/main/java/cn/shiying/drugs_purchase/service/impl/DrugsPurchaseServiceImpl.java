package cn.shiying.drugs_purchase.service.impl;

import cn.shiying.common.dto.Result;
import cn.shiying.common.entity.Drugs.DrugsDetailed;
import cn.shiying.common.entity.Drugs.DrugsPurchaseDetailed;
import cn.shiying.common.entity.supplier.SupplierDetailed;
import cn.shiying.common.enums.ErrorEnum;
import cn.shiying.common.exception.ExceptionCast;
import cn.shiying.common.exception.ExceptionCatch;
import cn.shiying.drugs_purchase.client.ActivitiClient;
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

    @Autowired
    private ActivitiClient activitiClient;

    /**
     * 分页查询
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params,Integer check) {
        Page page=new Query<DrugsPurchase>(params).getPage();
        Result result = null;
        if (check==0) {
            result = activitiClient.checkOrder();
        }else if (check==1){
            result = activitiClient.order();
        }else {
            result = activitiClient.warehouseCheck();
        }
        if ((Integer) result.get("code") != 200) {
            ExceptionCast.cast(ErrorEnum.UNKNOWN);
        }
        List<String> ids= (List<String>) result.get("ids");
        if (ids == null || ids.size()==0) {
            page.setRecords(null);
            return new PageUtils(page);
        }

        List<PurchaseSupplierVo> list= baseMapper.DrugsPurchaseList(page,params,ids);
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

        Result result= activitiClient.startDrug(PurchaseId);
        if ((Integer) result.get("code")!=200) ExceptionCast.cast(ErrorEnum.LOAD_TIME_LANG);
        String processInstanceId = (String) result.get("processInstanceId");

        //循环Drugs数据
        List<Drugs> DrugsList=drugsAndDetailed.getDetailed();
        Double AllTotal=0.0;
        for (Drugs drugs : DrugsList) {
            double price= drugs.getPdMoney();
            Integer drugsNum = drugs.getPdNum();
            double total = price*drugsNum;
            AllTotal+=total;
        }

        //药品购入表
        DrugsPurchase dp=new DrugsPurchase();
        dp.setPurchaseId(PurchaseId);//订单编号
        dp.setSupplierId(drugsAndDetailed.getSupplierId());//供应商编号
        dp.setPurchaseAmountPayable(AllTotal);//应付金额
        dp.setPurchaseActualAmountPaid(drugsAndDetailed.getPayPrice());//已付订金
        dp.setProcessInstanceId(processInstanceId);
        baseMapper.insert(dp);

        //药品购入详细表
        List<DrugsPurchaseDetailed> as=new ArrayList<>();
        DrugsPurchaseDetailed dpd;
        //循环Drugs数据
        List<Drugs> list=drugsAndDetailed.getDetailed();
        for (Drugs drugs : list) {
            dpd=new DrugsPurchaseDetailed();
            dpd.setDrugsId(drugs.getDrugsId());//药品编号
            dpd.setPurchaseId(dp.getPurchaseId());//药品购入表编号
            dpd.setPdMoney(drugs.getPdMoney());//价格
            dpd.setPdNum(drugs.getPdNum());//数量
            as.add(dpd);
        }
        baseMapper.addDrugsPurchaseDetailed(as);
    }

    @Override
    public List<DrugsPurchaseDetailed> getByDrugsId(String id) {
        return baseMapper.getByDrugsId(id);
    }

    //根据单号修改进货表
    @Override
    public void updateDrugs(DrugsAndDetailed detailed) {
        //循环Drugs数据
        List<Drugs> DrugsList=detailed.getDetailed();
        Double AllTotal=0.0;
        for (Drugs drugs : DrugsList) {
            double price= drugs.getPdMoney();
            Integer drugsNum = drugs.getPdNum();
            double total = price*drugsNum;
            AllTotal+=total;
        }

        Result result= activitiClient.startDrug(detailed.getPurchaseId());
        if ((Integer) result.get("code")!=200) ExceptionCast.cast(ErrorEnum.LOAD_TIME_LANG);
        String processInstanceId = (String) result.get("processInstanceId");

        //药品购入表
        DrugsPurchase dp=new DrugsPurchase();
        dp.setPurchaseId(detailed.getPurchaseId());//订单编号
        dp.setSupplierId(detailed.getSupplierId());//供应商编号
        dp.setPurchaseAmountPayable(AllTotal);//应付金额
        dp.setPurchaseActualAmountPaid(detailed.getPayPrice());//已付订金
        dp.setProcessInstanceId(processInstanceId);
        System.out.println("药品表数据："+dp);
        baseMapper.updateDrugs(dp);
    }

    //根据单号删除进货详细表
    @Override
    public void delDrugs(String purchaseId) {
        baseMapper.delDrugs(purchaseId);
    }

    //再添加进货详细表
    @Override
    public void addDrugs(DrugsAndDetailed detailed) {
        List<DrugsPurchaseDetailed> as=new ArrayList<>();
        DrugsPurchaseDetailed dpd;
        //循环Drugs数据
        List<Drugs> list=detailed.getDetailed();
        for (Drugs drugs : list) {
            dpd=new DrugsPurchaseDetailed();
            dpd.setDrugsId(drugs.getDrugsId());//药品编号
            dpd.setPurchaseId(detailed.getPurchaseId());//药品购入表编号
            dpd.setPdMoney(drugs.getPdMoney());//价格
            dpd.setPdNum(drugs.getPdNum());//数量
            as.add(dpd);
        }
        baseMapper.addDrugsPurchaseDetailed(as);
    }

    @Override
    public PageUtils historyOrder(Map<String, Object> params){
        Page page=new Query<DrugsPurchase>(params).getPage();
        List<PurchaseSupplierVo> list= baseMapper.pageList(page,params);
        List<String> processInstanceIds=new ArrayList<>();
        for (PurchaseSupplierVo purchaseSupplierVo : list) {
            processInstanceIds.add(purchaseSupplierVo.getProcessInstanceId());
        }
        Result result = activitiClient.bpmName(processInstanceIds);
        if ((Integer) result.get("code")!=200) ExceptionCast.cast(ErrorEnum.LOAD_TIME_LANG);
        Map<String,String> map=(Map<String, String>) result.get("map");
        for (PurchaseSupplierVo purchaseSupplierVo : list) {
            purchaseSupplierVo.setBpmName(map.get(purchaseSupplierVo.getProcessInstanceId()));
        }
        page.setRecords(list);
        return new PageUtils(page);
    }



}
