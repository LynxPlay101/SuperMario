package me.lynxplay.supermario.engine.world.blocks.material;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class MaterialAdapter extends XmlAdapter<String, Material> {

    private final MaterialManager manager;

    public MaterialAdapter(MaterialManager manager) {
        this.manager = manager;
    }

    @Override
    public Material unmarshal(String v) throws Exception {
        return manager.find(v).orElse(null);
    }

    @Override
    public String marshal(Material v) throws Exception {
        return v.getId();
    }
}
