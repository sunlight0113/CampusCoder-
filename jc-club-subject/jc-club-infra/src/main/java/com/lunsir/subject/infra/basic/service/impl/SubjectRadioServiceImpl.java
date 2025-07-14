package com.lunsir.subject.infra.basic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lunsir.subject.infra.basic.dao.SubjectRadioDao;
import com.lunsir.subject.infra.basic.entity.SubjectRadio;
import com.lunsir.subject.infra.basic.service.SubjectRadioService;
import org.springframework.stereotype.Service;

/**
 * 单选题信息表(SubjectRadio)表服务实现类
 *
 * @author makejava
 * @since 2024-03-03 15:29:19
 */
@Service("subjectRadioService")
public class SubjectRadioServiceImpl extends ServiceImpl<SubjectRadioDao, SubjectRadio> implements SubjectRadioService {

}

