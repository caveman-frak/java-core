package uk.co.bluegecko.core.swing.components;


import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import uk.co.bluegecko.core.util.Safe;


/**
 * A Panel that supports setting a max and/or minimum size.
 * If either dimension is set to {@link uk.co.bluegecko.core.swing.components.SizedHelper#USE_PREFERRED_SIZE} then that
 * dimension will be locked to the preferred size.
 * If either dimension is set to {@link uk.co.bluegecko.core.swing.components.SizedHelper#UNRESTRICTED_SIZE} then that
 * dimension will be unconstrained.
 */
public class TitledPanel extends JPanel
{

	private static final long serialVersionUID = -5540281756159591216L;

	private String title;
	private Border titleBorder;
	private int titleJustification;
	private int titlePosition;

	/**
	 * Create a panel with a title.
	 *
	 * @param title
	 *            title to use
	 */
	public TitledPanel( final String title )
	{
		super();

		setTitle( title );
	}

	/**
	 * Return the title of the panel.
	 *
	 * @return the title
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 * Set the title of the panel.
	 *
	 * @param title
	 *            the title
	 */
	public void setTitle( final String title )
	{
		if ( !Safe.equals( this.title, title ) )
		{
			this.title = title;

			updateBorder();
		}
	}

	/**
	 * Get the border for the title.
	 *
	 * @return the border
	 */
	public Border setTitleBorder()
	{
		return titleBorder;
	}

	/**
	 * Get the border for the title.
	 *
	 * @param titleBorder
	 *            the border
	 */
	public void setTitleBorder( final Border titleBorder )
	{
		if ( !Safe.equals( this.titleBorder, titleBorder ) )
		{
			this.titleBorder = titleBorder;

			updateBorder();
		}
	}

	/**
	 * Get the title justification.
	 *
	 * @return the justification
	 */
	public int getTitleJustification()
	{
		return titleJustification;
	}

	/**
	 * Set the title justification.
	 *
	 * @param titleJustification
	 *            the justification
	 */
	public void setTitleJustification( final int titleJustification )
	{
		if ( !Safe.equals( this.titleJustification, titleJustification ) )
		{
			this.titleJustification = titleJustification;

			updateBorder();
		}
	}

	/**
	 * Get the title position.
	 *
	 * @return the position
	 */
	public int getTitlePosition()
	{
		return titlePosition;
	}

	/**
	 * Set the title position.
	 *
	 * @param titlePosition
	 *            the position
	 */
	public void setTitlePosition( final int titlePosition )
	{
		if ( !Safe.equals( this.titlePosition, titlePosition ) )
		{
			this.titlePosition = titlePosition;

			updateBorder();
		}
	}

	private void updateBorder()
	{
		setBorder( new TitledBorder( titleBorder, title, titleJustification, titlePosition ) );
	}

}
