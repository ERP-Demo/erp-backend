package cn.shiying.prescription.service.impl;

import cn.shiying.common.dto.Result;
import cn.shiying.common.entity.patient.PatientDetailed;
import cn.shiying.common.enums.ErrorEnum;
import cn.shiying.common.exception.ExceptionCast;
import cn.shiying.prescription.client.DrugsClient;
import cn.shiying.prescription.entity.Prescription;
import cn.shiying.prescription.entity.PrescriptionDetails;
import cn.shiying.prescription.entity.Prescription_Vo;
import cn.shiying.prescription.entity.from.DrugsAndDetailed;
import cn.shiying.prescription.mapper.PrescriptionMapper;
import cn.shiying.prescription.service.PrescriptionService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.shiying.common.utils.Query;
import cn.shiying.common.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author tyb
 * @since 2020-04-30
 */
@Service
public class PrescriptionServiceImpl extends ServiceImpl<PrescriptionMapper, Prescription> implements PrescriptionService {

    @Autowired
    DrugsClient drugsClient;

    /**
     * 分页查询
     *
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<Prescription> page = baseMapper.selectPage(new Query<Prescription>(params).getPage(),
                new QueryWrapper<Prescription>().lambda());
        return new PageUtils(page);
    }

    //    @Override
//    public void addDrugsAndDetailed(List<DrugsAndDetailed> drugsAndDetailed) {
//        String prescriptionId=schedule.createId();
//        List<Drugs> DrugsList=drugsAndDetailed.getDetailed();
//        Double AllTotal=0.0;
//        for (Drugs drugs : DrugsList) {
//            Integer price=drugs.getDrugsPrice();
//            Integer num=drugs.getDrugsNum();
//            Integer total = price*num;
//            AllTotal+=total;
//        }
//        Prescription prescription=new Prescription();
//        prescription.setPrescriptionId(prescriptionId);
//        prescription.setPrescriptionName(drugsAndDetailed.getPrescriptionName());
//        prescription.setPrescriptionPrice(AllTotal);
//        baseMapper.addpPrescription(prescription);
//        List<PrescriptionDetails> as=new ArrayList<>();
//        PrescriptionDetails pd;
//        List<Drugs> list=drugsAndDetailed.getDetailed();
//        for (Drugs drugs : list) {
//            pd=new PrescriptionDetails();
//            pd.setDrugsId(drugs.getDrugsId());
//            pd.setDrugsNum(drugs.getDrugsNum());
//            pd.setDrugsUse(drugs.getDrugsUse());
//            pd.setDrugsDay(drugs.getDrugsDay());
//            pd.setDrugsUsenum(drugs.getDrugsUsenum());
//            as.add(pd);
//        }
//        baseMapper.addpPrescriptionDetails(as);
//    }
    @Override
    public Map<String, String> addDrugsAndDetailed(List<DrugsAndDetailed> drugsAndDetailed) {
        List<Integer> drugIds = new ArrayList<>();
        for (DrugsAndDetailed andDetailed : drugsAndDetailed) {
            for (PrescriptionDetails prescriptionDetails : andDetailed.getDruglist()) {
                drugIds.add(prescriptionDetails.getDrugsId());
            }
        }
        Result result = drugsClient.getPrice(drugIds);
        if ((Integer) result.get("code") != 200) ExceptionCast.cast(ErrorEnum.UNKNOWN);
        Map<String, Double> map = (Map<String, Double>) result.get("map");
        Map<String, String> map1=new HashMap<>();
        for (DrugsAndDetailed andDetailed : drugsAndDetailed) {
            Prescription prescription = new Prescription();
            List<PrescriptionDetails> druglist = andDetailed.getDruglist();
            double price = 0.0;
            for (PrescriptionDetails prescriptionDetails : druglist) {
                price += map.get(prescriptionDetails.getDrugsId()+"")*prescriptionDetails.getDrugsNum();
            }
            prescription.setPrescriptionPrice(price);
            prescription.setPrescriptionName(andDetailed.getPrescriptionName());
            prescription.setPrescriptionState(0);
            baseMapper.insert(prescription);
            map1.put(andDetailed.getRowId()+"",prescription.getPrescriptionId()+"");
            for (PrescriptionDetails prescriptionDetails : druglist) {
                prescriptionDetails.setPrescriptionId(prescription.getPrescriptionId());
            }
            baseMapper.addpPrescriptionDetails(druglist);
        }
        return map1;
    }

    public void toVoid(List<Integer> ids) {
        baseMapper.toVoid(ids);
    }

    @Override
    public List<Prescription_Vo> PrescriptionVoByid(Integer[] id) {
        List<Prescription_Vo> vo = new ArrayList<>();
        for (Integer integer : id) {
            Prescription_Vo prescription_vos = baseMapper.PrescriptionVoByid(integer);
            vo.add(prescription_vos);
        }
        return vo;
    }

    @Override
    public void updatestate(Integer[] id) {
        for (Integer integer : id) {
            baseMapper.updatestate(integer);
        }
    }

    @Override
    public PageUtils queryPagePre(Map<String, Object> params) {
        Page page=new Query<Prescription_Vo>(params).getPage();
        List<Prescription_Vo> list= baseMapper.PrescriptionVo(page,params);
        page.setRecords(list);
        return new PageUtils(page);
    }
}
