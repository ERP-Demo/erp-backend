package cn.shiying.patient_handle.service.impl;

import cn.shiying.common.dto.Result;
import cn.shiying.common.entity.Requirements;
import cn.shiying.common.entity.patient.PatientDetailed;
import cn.shiying.common.entity.patient_handle.PatientHandle;
import cn.shiying.common.entity.patient_handle.PatientHandleApplyDetailed;
import cn.shiying.patient_handle.mapper.PatientHandleMapper;
import cn.shiying.patient_handle.service.PatientHandleApplyDetailedService;
import cn.shiying.patient_handle.service.PatientHandleApplyService;
import cn.shiying.patient_handle.service.PatientHandleService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.shiying.common.utils.Query;
import cn.shiying.common.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tyb
 * @since 2020-05-11
 */
@Service
public class PatientHandleServiceImpl extends ServiceImpl<PatientHandleMapper, PatientHandle> implements PatientHandleService {

    @Autowired
    private PatientHandleService handleService;

    @Autowired
    PatientHandleApplyService applyService;

    @Autowired
    PatientHandleApplyDetailedService detailedService;

    /**
     * 分页查询
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper<PatientHandle> wrapper = new QueryWrapper<>();
        if (params.get("search")!=null&&params.get("search")!="")
            wrapper.like("handle_name",params.get("search"));
        IPage<PatientHandle> page=baseMapper.selectPage(new Query<PatientHandle>(params).getPage(),
                wrapper.lambda());


        return new PageUtils(page);
    }

    @Override
    public void updatestate(Integer[] id) {
        for (Integer integer : id) {
            baseMapper.updatestate(integer);
        }
    }

    @Override
    public PatientHandleApplyDetailed getPatientDeId(Integer patientId) {
        return baseMapper.getPatientDeId(patientId);
    }

    @Override
    public PageUtils queryPagePatient(Map<String, Object> params) {

        IPage<PatientHandleApplyDetailed> page = detailedService.page(new Page<PatientHandleApplyDetailed>(), new QueryWrapper<PatientHandleApplyDetailed>().eq("status", 1));
        Iterator<PatientHandleApplyDetailed> iterator = page.getRecords().iterator();
        while (iterator.hasNext()){
            PatientHandleApplyDetailed detailed = iterator.next();
            detailed.setPatientHandleApply(applyService.getById(detailed.getApplyId()));
            Integer patientId=detailed.getPatientHandleApply().getPatientId();
            PatientHandleApplyDetailed p=handleService.getPatientDeId(patientId);
            if (params.get("key")!=null&&!p.getPatientName().contains(params.get("key")+"")){
                iterator.remove();
                continue;
            }
            detailed.setPatientHandle(handleService.getById(detailed.getHandleId()));
            detailed.setPatientName(p.getPatientName());
        }
        return new PageUtils(page);
    }

}
