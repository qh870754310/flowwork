package com.qh.modules.sh.utils;

import com.qh.modules.common.utils.SpringContextUtils;
import com.qh.modules.common.utils.StringUtils;
import com.qh.modules.sh.entity.ShFamilyMemberEntity;
import com.qh.modules.sys.service.CodeService;

import java.util.List;

/**
 * Created by Administrator on 2018/8/20.
 */
public class FilterUtil {

    private static CodeService codeService = SpringContextUtils.getBean(CodeService.class);

    public static void filter_ShFamilyMemberEntity(List<ShFamilyMemberEntity> shFamilyMemberList) {
        shFamilyMemberList.stream().forEach(
                s -> {
                    if (!StringUtils.isEmpty(s.getDomesticRelation())) {
                        s.setRelation_code(codeService.queryObject(s.getDomesticRelation()).getName());
                    }
                    if (!StringUtils.isEmpty(s.getPoliticalStatus())) {
                        s.setPolitical_code(codeService.queryObject(s.getPoliticalStatus()).getName());
                    }
                    if (!StringUtils.isEmpty(s.getCertificateType())) {
                        s.setCertificate_code(codeService.queryObject(s.getCertificateType()).getName());
                    }
                    if (!StringUtils.isEmpty(s.getHealthCondition())) {
                        s.setHealth_code(codeService.queryObject(s.getHealthCondition()).getName());
                    }
                    if (!StringUtils.isEmpty(s.getMaritalStatus())) {
                        s.setMarital_code(codeService.queryObject(s.getMaritalStatus()).getName());
                    }
                }
        );
    }
}
