package edu.ifpb.dac.infraestrutura;

import java.util.List;
import java.util.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Samuell
 */
public interface Dao {

    public void save(Object o);

    public Object find(Class classe, Object object);

    public void update(Object o);

    public Object getSingleResult(String consulta);

    public List getResultList(String consulta);

    public List getResultList(String consulta, Map<String, Object> parametros);
    
    public List getResultList(String consulta, int min, int max);
}
