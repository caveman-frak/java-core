package uk.co.bluegecko.core.swing.components;


import java.awt.Dimension;
import java.awt.event.ComponentListener;


/**
 * Interface to define various component size methods.
 */
public interface Sized
{

	/**
	 * Add a component listener.
	 *
	 * @param listener
	 *            listener to add to component
	 */
	public void addComponentListener( ComponentListener listener );

	/**
	 * Return the component size.
	 *
	 * @return component size
	 */
	public Dimension getSize();

	/**
	 * Set the component size
	 * 
	 * @param size
	 *            the size
	 */
	public void setSize( Dimension size );

	/**
	 * Return the component size.
	 *
	 * @return component size
	 */
	public Dimension getMinimumSize();

	/**
	 * Return the component preferred size.
	 *
	 * @return component size
	 */
	public Dimension getPreferredSize();

	/**
	 * Return the component maximum size.
	 *
	 * @return component size
	 */
	public Dimension getMaximumSize();

}
