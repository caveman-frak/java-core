/**
 * Copyright 2015, <a href="http://bluegecko.co.uk/java-core">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.model;


import java.io.Serializable;


/**
 * An extension of model that understands identity.
 *
 * @param <K>
 *            the type of id
 */
public interface Data< K extends Serializable & Comparable< K >> extends Model, Keyed< K >, Comparable< Data< K >>
{}
