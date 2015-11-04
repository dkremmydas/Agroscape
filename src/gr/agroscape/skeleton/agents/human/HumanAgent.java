/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.agroscape.skeleton.agents.human;

import gr.agroscape.skeleton.agents.AgroscapeAgent;

import org.apache.commons.collections.map.HashedMap;

import repast.simphony.space.grid.GridPoint;


/**
 * A human agent of AgroScape.
 * <p>This abstract class defines the data shared by all implemented classes: 
 * <ul>
 * <li>Automatic assignment of ID to each agent (see static {@link #myId})</li>
 * <li>Having a reference to the MainContext (see {@link #mainContext})
 * <li>A {@link HashedMap} of properties</li>
 * </ul>
 * </p> 
 * @author Dimitris Kremmydas
 */
public abstract class HumanAgent extends AgroscapeAgent {
    
    
    
    /**
     * Where has this Agent its residence
     * //TODO implement it somehow
     */
    protected GridPoint residentIn;

    
    
}
