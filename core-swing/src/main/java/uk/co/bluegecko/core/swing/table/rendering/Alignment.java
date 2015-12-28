package uk.co.bluegecko.core.swing.table.rendering;


import java.awt.Component;

import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


/**
 * Define alignment of components
 */
@SuppressWarnings( "javadoc" )
public enum Alignment
{

	LEFT( SwingConstants.LEFT ), LEADING( SwingConstants.LEADING ), CENTER( SwingConstants.CENTER ), RIGHT(
			SwingConstants.RIGHT ), TRAILING( SwingConstants.TRAILING );

	private final int alignment;

	private Alignment( final int alignment )
	{
		this.alignment = alignment;
	}

	/**
	 * Apply the alignment to a component
	 * 
	 * @param c
	 *            the component
	 */
	public void apply( final Component c )
	{
		if ( c instanceof JLabel )
		{
			( ( JLabel ) c ).setHorizontalAlignment( alignment );
		}
		else if ( c instanceof JTextField )
		{
			( ( JTextField ) c ).setHorizontalAlignment( alignment );
		}
		else if ( c instanceof AbstractButton )
		{
			( ( AbstractButton ) c ).setHorizontalAlignment( alignment );
		}
		else if ( c instanceof JComponent )
		{
			final JComponent comp = ( JComponent ) c;
			switch ( alignment )
			{
				case SwingConstants.LEFT:
				case SwingConstants.LEADING:
					comp.setAlignmentY( Component.LEFT_ALIGNMENT );
					break;
				case SwingConstants.CENTER:
					comp.setAlignmentY( Component.CENTER_ALIGNMENT );
					break;
				case SwingConstants.RIGHT:
				case SwingConstants.TRAILING:
					comp.setAlignmentY( Component.RIGHT_ALIGNMENT );
					break;
				default:
					break;
			}
		}
	}

}