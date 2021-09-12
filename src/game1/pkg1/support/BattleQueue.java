package game1.pkg1.support;

import game1.pkg1.entities.Entity;
import game1.pkg1.entities.creatures.Ally;
import game1.pkg1.entities.creatures.Enemy;
import game1.pkg1.entities.creatures.Fighter;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author acob1
 */
public class BattleQueue {

    ArrayList<BattleOrderNode> order;
    private ArrayList<BattleOrderNode> pOrder; //predict Order
    Comparator<BattleOrderNode> compareTTM = new CompareBattleNodes();
    private Fighter active;
    public byte animTick;
    public boolean isAnimDone;
    private final byte ANIM_TIME=60;

    public BattleQueue() {
        order = new ArrayList<BattleOrderNode>();
        animTick = 0;
        isAnimDone = true;
    }

    public void add(Fighter acting, int ttm) {
        order.add(new BattleOrderNode(acting, ttm));
        sort();
    }
    
    public void dump(){
        for(BattleOrderNode x : order){
            System.out.printf("%s\t\t%d\n", x.name,x.timeToMove);
        }
    }
    
    public void predict(Fighter actor, int[] TTMeffects, Fighter... target){
        
    }
    
    //NEED AN ANIMATOR FACTOR CLASS, OR A VIEW CLASS
    
    public void animate(){
        if(animTick<ANIM_TIME){
            animTick++;
            isAnimDone = false;
        }if(animTick>=ANIM_TIME){
            isAnimDone = true;
        }
    }
    
    public void commit(){
        //temp
        int maxTTM = 0;
        for(BattleOrderNode x : order){
            if(x.id == active.getId()){
                if(x.getTTM()> maxTTM){
                    maxTTM = x.getTTM();
                }
            }
        }
        order.add(new BattleOrderNode(active, maxTTM + active.getSpeed()));
        int toMinus = order.get(0).getTTM();
        for(BattleOrderNode x : order){
            x.timeToMove -= toMinus;
        }
        order.remove(0);
        sort();
        active = order.get(0).getSoldier();
        //END temp
    }
    
    public void commit(Fighter active, Fighter target, int TTMeffect){
        //temp
        
        //END temp
    }

    public ArrayList<BattleOrderNode> getOrder() {
        return order;
    }
    
    public Fighter getActive(){
        return active;
    }
    
    private void sort(){
        Collections.sort(order, compareTTM);
        active = order.get(0).getSoldier();
    }

    public class BattleOrderNode {

        Fighter soldier;
        String name;
        BufferedImage icon;
        long id;
        int timeToMove;

        public BattleOrderNode(Fighter x, int ttm) {
            soldier = x;
            name = x.getName();
            id = x.getId();
            icon = x.getIcon();
            timeToMove = ttm;
        }

        public int getTTM() {
            return timeToMove;
        }

        public Fighter getSoldier() {
            return soldier;
        }
        
        public BufferedImage getIcon(){
            return icon;
        }
    }

    private class CompareBattleNodes implements Comparator<BattleOrderNode> {

        public int compare(BattleOrderNode o1, BattleOrderNode o2) { //-1 is used to denote that o2 is greater
            //return Integer.compare(o1.getTTM(), o2.getTTM());
            if (o1.getTTM() > o2.getTTM()) {
                return 1;
            } else if (o1.getTTM() == o2.getTTM()) {
                if (o1.getSoldier() instanceof Ally && o2.getSoldier() instanceof Enemy) {
                    return -1;
                } else if (o1.getSoldier() instanceof Enemy && o2.getSoldier() instanceof Ally) {
                    return 1;
                } else if (o1.getSoldier() instanceof Ally && o2.getSoldier() instanceof Ally) {
                    if (o1.getSoldier().getSpeed() > o2.getSoldier().getSpeed()) {
                        return -1; //the slower ally goes first
                    } else {
                        return 1;
                    }
                } else if (o1.getSoldier() instanceof Enemy && o1.getSoldier() instanceof Enemy) {
                    if (o1.getSoldier().getSpeed() > o2.getSoldier().getSpeed()) {
                        return -1; //the slower enemy goes first
                    } else {
                        return 1;
                    }
                } else {
                    return 1;
                }
            } else {
                return -1;
            }
        }
    }

}
