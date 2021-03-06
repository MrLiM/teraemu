package com.angelis.tera.game.services;

import java.util.List;
import java.util.Set;

import javolution.util.FastList;

import org.apache.log4j.Logger;

import com.angelis.tera.game.models.CreatureCurrentStats;
import com.angelis.tera.game.models.Gather;
import com.angelis.tera.game.models.TeraCreature;
import com.angelis.tera.game.models.WorldPosition;
import com.angelis.tera.game.models.creature.CreatureTemplate;
import com.angelis.tera.game.xml.entity.CreatureEntity;
import com.angelis.tera.game.xml.entity.CreatureEntityHolder;
import com.angelis.tera.game.xml.entity.CreatureSpawnEntity;
import com.angelis.tera.game.xml.entity.CreatureTemplateEntity;
import com.angelis.tera.game.xml.entity.GatherEntity;
import com.angelis.tera.game.xml.entity.GatherEntityHolder;
import com.angelis.tera.game.xml.entity.SpawnEntity;

public class SpawnService extends AbstractService {

    /** LOGGER */
    private static Logger log = Logger.getLogger(SpawnService.class.getName());

    /** INSTANCE */
    private static SpawnService instance;
    
    private List<TeraCreature> creatures = new FastList<>();
    private List<Gather> gathers = new FastList<>();
    
    private SpawnService() {}
    
    @Override
    public void onInit() {
        // TODO this should be done in mapper !!!!
        Set<CreatureEntity> creatureEntities = XMLService.getInstance().getEntity(CreatureEntityHolder.class).getCreatures();
        for (CreatureEntity creatureEntity : creatureEntities) {
            for (CreatureSpawnEntity spawnEntity : creatureEntity.getCreatureSpawns()) {
                TeraCreature teraCreature = new TeraCreature(creatureEntity.getId());
                teraCreature.setCreatureType(creatureEntity.getCreatureType());
                teraCreature.setModelId(creatureEntity.getModelId());
                teraCreature.setAggresive(creatureEntity.isInoffensive() == 0);
                
                // Stats
                CreatureCurrentStats creatureCurrentStats = new CreatureCurrentStats();
                creatureCurrentStats.setSpeed(creatureEntity.getSpeed());
                teraCreature.setCreatureCurrentStats(creatureCurrentStats);
                
                
                // World position
                teraCreature.setWorldPosition(new WorldPosition(spawnEntity.getMapId(), spawnEntity.getX(), spawnEntity.getY(), spawnEntity.getZ(), spawnEntity.getHeading()));
                
                // Template
                CreatureTemplate creatureTemplate = new CreatureTemplate();
                final CreatureTemplateEntity creatureEntityTemplate = creatureEntity.getCreatureTemplate();
                if (creatureEntityTemplate != null) {
                    creatureTemplate.setCreatureTitle(creatureEntityTemplate.getCreatureTitle());
                    creatureTemplate.setHuntingZoneId(creatureEntityTemplate.getHuntingZoneId());
                }
                teraCreature.setCreatureTemplate(creatureTemplate);
                creatures.add(teraCreature);
            }
        }
        
        Set<GatherEntity> gatherEntities = XMLService.getInstance().getEntity(GatherEntityHolder.class).getGathers();
        for (GatherEntity gatherEntity : gatherEntities) {
            for (SpawnEntity spawnEntity : gatherEntity.getSpawns()) {
                Gather gather = new Gather(gatherEntity.getId());
                gather.setWorldPosition(new WorldPosition(spawnEntity.getMapId(), spawnEntity.getX(), spawnEntity.getY(), spawnEntity.getZ()));
                gathers.add(gather);
            }
        }
        
        log.info("SpawnService started");
    }

    @Override
    public void onDestroy() {
    }
    
    public List<TeraCreature> getCreaturesByMapId(int mapId) {
        List<TeraCreature> creatures = new FastList<>();
        for (TeraCreature teraCreature : this.creatures) {
            if (teraCreature.getWorldPosition().getMapId() == mapId) {
                creatures.add(teraCreature);
            }
        }
        return creatures;
    }
    
    public List<Gather> getGathersByMapId(int mapId) {
        List<Gather> gathers = new FastList<>();
        for (Gather gather : this.gathers) {
            if (gather.getWorldPosition().getMapId() == mapId) {
                gathers.add(gather);
            }
        }
        return gathers;
    }
    
    public TeraCreature getCreatureByUid(int creatureUId) {
        TeraCreature creature = null;
        for (TeraCreature teraCreature : this.creatures) {
            if (teraCreature.getUid() == creatureUId) {
                creature = teraCreature;
                break;
            }
        }
        return creature;
    }

    /** SINGLETON */
    public static SpawnService getInstance() {
        if (instance == null) {
            instance = new SpawnService();
        }

        return instance;
    }
}
