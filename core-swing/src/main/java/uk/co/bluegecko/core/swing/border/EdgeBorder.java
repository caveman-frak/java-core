package uk.co.bluegecko.core.swing.border;


import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.AbstractBorder;

import uk.co.bluegecko.core.swing.components.TitledPanel;


/**
 * Border applied to selected sides of a component.
 *
 */
public class EdgeBorder extends AbstractBorder
{

	private static final long serialVersionUID = 5802681543832507236L;

	public enum EdgeStyle
	{
		NONE( 0 ), LOWERED( 2 ), RAISED( 2 ), LINE( 1 );

		private final int offset;

		private EdgeStyle( final int offset )
		{
			this.offset = offset;
		}

		public int getOffset()
		{
			return offset;
		}
	}

	private final EdgeStyle top;
	private final EdgeStyle left;
	private final EdgeStyle bottom;
	private final EdgeStyle right;
	protected final Color highlight;
	protected final Color shadow;
	protected final Color color;

	private EdgeBorder( final Color color, final Color highlight, final Color shadow, final EdgeStyle top,
			final EdgeStyle left, final EdgeStyle bottom, final EdgeStyle right )
	{
		this.color = color;
		this.highlight = highlight;
		this.shadow = shadow;
		this.top = top;
		this.left = left;
		this.bottom = bottom;
		this.right = right;
	}

	/**
	 * Create a new edge border.
	 *
	 * @param highlight
	 *            highlight colour
	 * @param shadow
	 *            shadow colour
	 * @param top
	 *            top style
	 * @param left
	 *            left style
	 * @param bottom
	 *            bottom style
	 * @param right
	 *            right style
	 */
	public EdgeBorder( final Color highlight, final Color shadow, final EdgeStyle top, final EdgeStyle left,
			final EdgeStyle bottom, final EdgeStyle right )
	{
		this( null, highlight, shadow, top, left, bottom, right );
	}

	/**
	 * Create a new edge border.
	 *
	 * @param color
	 *            border colour
	 * @param top
	 *            top style
	 * @param left
	 *            left style
	 * @param bottom
	 *            bottom style
	 * @param right
	 *            right style
	 */
	public EdgeBorder( final Color color, final EdgeStyle top, final EdgeStyle left, final EdgeStyle bottom,
			final EdgeStyle right )
	{
		this( color, color.brighter(), color.darker(), top, left, bottom, right );
	}

	/**
	 * Create a new edge border.
	 *
	 * @param top
	 *            top style
	 * @param left
	 *            left style
	 * @param bottom
	 *            bottom style
	 * @param right
	 *            right style
	 */
	public EdgeBorder( final EdgeStyle top, final EdgeStyle left, final EdgeStyle bottom, final EdgeStyle right )
	{
		this( null, null, null, top, left, bottom, right );
	}

	/**
	 * Create a new edge border.
	 *
	 * @param style
	 *            common style
	 */
	public EdgeBorder( final EdgeStyle style )
	{
		this( style, style, style, style );
	}

	/**
	 * Create a new edge border.
	 */
	public EdgeBorder()
	{
		this( EdgeStyle.LOWERED );
	}

	@Override
	public void paintBorder( final Component c, final Graphics g, final int x, final int y, final int width,
			final int height )
	{
		final int w = width;
		final int h = height;
		g.translate( x, y );
		if ( top != null && top != EdgeStyle.NONE )
		{
			g.setColor( getLeadingColor( c, top ) );
			g.drawLine( 0, 0, w - ( right == EdgeStyle.NONE ? 1 : 2 ), 0 );
			if ( top != EdgeStyle.LINE )
			{
				g.setColor( getTrailingColor( c, top ) );
				g.drawLine( left == EdgeStyle.NONE ? 0 : 1, 1, w - ( right == EdgeStyle.NONE ? 1 : 3 ), 1 );
			}
		}
		if ( left != null && left != EdgeStyle.NONE )
		{
			g.setColor( getLeadingColor( c, left ) );
			g.drawLine( 0, 0, 0, h - ( bottom == EdgeStyle.NONE ? 1 : 2 ) );
			if ( left != EdgeStyle.LINE )
			{
				g.setColor( getTrailingColor( c, left ) );
				g.drawLine( 1, top == EdgeStyle.NONE ? 0 : 1, 1, h - ( bottom == EdgeStyle.NONE ? 1 : 3 ) );
			}
		}
		if ( bottom != null && bottom != EdgeStyle.NONE )
		{
			if ( bottom != EdgeStyle.LINE )
			{
				g.setColor( getLeadingColor( c, bottom ) );
				g.drawLine( 0, h - 2, w - ( right == EdgeStyle.NONE ? 1 : 2 ), h - 2 );
			}
			g.setColor( getTrailingColor( c, bottom ) );
			g.drawLine( 0, h - 1, w - 1, h - 1 );
		}
		if ( right != null && right != EdgeStyle.NONE )
		{
			if ( right != EdgeStyle.LINE )
			{
				g.setColor( getLeadingColor( c, right ) );
				g.drawLine( w - 2, h - ( bottom == EdgeStyle.NONE ? 1 : 2 ), w - 2, 0 );
			}
			g.setColor( getTrailingColor( c, right ) );
			g.drawLine( w - 1, h - 1, w - 1, 0 );
		}
		g.translate( -x, -y );
	}

	@Override
	public Insets getBorderInsets( final Component c )
	{
		return getBorderInsets( c, new Insets( 0, 0, 0, 0 ) );
	}

	@Override
	public Insets getBorderInsets( final Component c, final Insets insets )
	{
		insets.set( top.getOffset(), left.getOffset(), bottom.getOffset(), right.getOffset() );
		return insets;
	}

	@Override
	public boolean isBorderOpaque()
	{
		return true;
	}

	private Color getLeadingColor( final Component c, final EdgeStyle style )
	{
		switch ( style )
		{
			case RAISED:
				return getHighlightColor( c );
			case LOWERED:
				return getShadowColor( c );
			case LINE:
			case NONE:
			default:
				return getColor( c );
		}
	}

	private Color getTrailingColor( final Component c, final EdgeStyle style )
	{
		switch ( style )
		{
			case RAISED:
				return getShadowColor( c );
			case LOWERED:
				return getHighlightColor( c );
			case LINE:
			case NONE:
			default:
				return getColor( c );
		}
	}

	/**
	 * Derive highlight colour from the passed component.
	 *
	 * @param c
	 *            component to sample
	 * @return highlight colour
	 */
	public Color getHighlightColor( final Component c )
	{
		return getHighlightColor() != null ? getHighlightColor() : c.getBackground()
				.brighter();
	}

	/**
	 * Get highlight colour.
	 *
	 * @return highligh colour
	 */
	public Color getHighlightColor()
	{
		return highlight;
	}

	/**
	 * Derive shadow colour from the passed component.
	 *
	 * @param c
	 *            component to sample
	 * @return shadow colour
	 */
	public Color getShadowColor( final Component c )
	{
		return getShadowColor() != null ? getShadowColor() : c.getBackground()
				.darker();
	}

	/**
	 * Get shadow colour.
	 *
	 * @return shadow colour
	 */
	public Color getShadowColor()
	{
		return shadow;
	}

	/**
	 * Derive colour from the passed component.
	 *
	 * @param c
	 *            component to sample
	 * @return colour
	 */
	public Color getColor( final Component c )
	{
		return getColor() != null ? getColor() : c.getForeground();
	}

	/**
	 * Get colour.
	 *
	 * @return colour
	 */
	public Color getColor()
	{
		return color;
	}

	public final static class Demo
	{

		public static void main( final String[] args )
		{
			final javax.swing.JFrame frame = new javax.swing.JFrame( "Edge Border Demo" );
			frame.setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE );
			class Label extends javax.swing.JLabel
			{

				private static final long serialVersionUID = 0L;

				Label( final String text )
				{
					super( text );
					setOpaque( true );
					setHorizontalAlignment( SwingConstants.CENTER );
				}
			}
			( ( javax.swing.JComponent ) frame.getContentPane() )
					.setBorder( javax.swing.BorderFactory.createEmptyBorder( 10, 10, 10, 10 ) );
			frame.getContentPane()
					.setLayout( new java.awt.GridLayout( 0, 1, 10, 10 ) );

			JPanel panel = new TitledPanel( "Full Borders" );
			panel.setLayout( new java.awt.GridLayout( 0, 4, 10, 10 ) );
			Label label = new Label( "Low Borders" );
			label.setBorder( new EdgeBorder( EdgeStyle.LOWERED ) );
			panel.add( label );
			label = new Label( "High Borders" );
			label.setBorder( new EdgeBorder( EdgeStyle.RAISED ) );
			panel.add( label );
			label = new Label( "Line Borders" );
			label.setBorder( new EdgeBorder( EdgeStyle.LINE ) );
			panel.add( label );
			label = new Label( "No Borders" );
			label.setBorder( new EdgeBorder( EdgeStyle.NONE ) );
			panel.add( label );
			label = new Label( "Red Lowered " );
			label.setBorder(
					new EdgeBorder( Color.RED, EdgeStyle.NONE, EdgeStyle.NONE, EdgeStyle.LOWERED, EdgeStyle.NONE ) );
			panel.add( label );
			label = new Label( "Red Raised" );
			label.setBorder(
					new EdgeBorder( Color.RED, EdgeStyle.NONE, EdgeStyle.NONE, EdgeStyle.RAISED, EdgeStyle.NONE ) );
			panel.add( label );
			frame.getContentPane()
					.add( panel );

			panel = new TitledPanel( "Raised Borders" );
			panel.setLayout( new java.awt.GridLayout( 0, 4, 10, 10 ) );
			label = new Label( "Top Border" );
			label.setBorder( new EdgeBorder( EdgeStyle.RAISED, EdgeStyle.NONE, EdgeStyle.NONE, EdgeStyle.NONE ) );
			panel.add( label );
			label = new Label( "Left Border" );
			label.setBorder( new EdgeBorder( EdgeStyle.NONE, EdgeStyle.RAISED, EdgeStyle.NONE, EdgeStyle.NONE ) );
			panel.add( label );
			label = new Label( "Bottom Border" );
			label.setBorder( new EdgeBorder( EdgeStyle.NONE, EdgeStyle.NONE, EdgeStyle.RAISED, EdgeStyle.NONE ) );
			panel.add( label );
			label = new Label( "Right Border" );
			label.setBorder( new EdgeBorder( EdgeStyle.NONE, EdgeStyle.NONE, EdgeStyle.NONE, EdgeStyle.RAISED ) );
			panel.add( label );
			label = new Label( "TL Borders" );
			label.setBorder( new EdgeBorder( EdgeStyle.RAISED, EdgeStyle.RAISED, EdgeStyle.NONE, EdgeStyle.NONE ) );
			panel.add( label );
			label = new Label( "BL Borders" );
			label.setBorder( new EdgeBorder( EdgeStyle.NONE, EdgeStyle.RAISED, EdgeStyle.RAISED, EdgeStyle.NONE ) );
			panel.add( label );
			label = new Label( "BR Borders" );
			label.setBorder( new EdgeBorder( EdgeStyle.NONE, EdgeStyle.NONE, EdgeStyle.RAISED, EdgeStyle.RAISED ) );
			panel.add( label );
			label = new Label( "TR Borders" );
			label.setBorder( new EdgeBorder( EdgeStyle.RAISED, EdgeStyle.NONE, EdgeStyle.NONE, EdgeStyle.RAISED ) );
			panel.add( label );
			frame.getContentPane()
					.add( panel );

			panel = new TitledPanel( "Lowered Borders" );
			panel.setLayout( new java.awt.GridLayout( 0, 4, 10, 10 ) );
			label = new Label( "Top Border" );
			label.setBorder( new EdgeBorder( EdgeStyle.LOWERED, EdgeStyle.NONE, EdgeStyle.NONE, EdgeStyle.NONE ) );
			panel.add( label );
			label = new Label( "Left Border" );
			label.setBorder( new EdgeBorder( EdgeStyle.NONE, EdgeStyle.LOWERED, EdgeStyle.NONE, EdgeStyle.NONE ) );
			panel.add( label );
			label = new Label( "Bottom Border" );
			label.setBorder( new EdgeBorder( EdgeStyle.NONE, EdgeStyle.NONE, EdgeStyle.LOWERED, EdgeStyle.NONE ) );
			panel.add( label );
			label = new Label( "Right Border" );
			label.setBorder( new EdgeBorder( EdgeStyle.NONE, EdgeStyle.NONE, EdgeStyle.NONE, EdgeStyle.LOWERED ) );
			panel.add( label );
			label = new Label( "TL Borders" );
			label.setBorder( new EdgeBorder( EdgeStyle.LOWERED, EdgeStyle.LOWERED, EdgeStyle.NONE, EdgeStyle.NONE ) );
			panel.add( label );
			label = new Label( "BL Borders" );
			label.setBorder( new EdgeBorder( EdgeStyle.NONE, EdgeStyle.LOWERED, EdgeStyle.LOWERED, EdgeStyle.NONE ) );
			panel.add( label );
			label = new Label( "BR Borders" );
			label.setBorder( new EdgeBorder( EdgeStyle.NONE, EdgeStyle.NONE, EdgeStyle.LOWERED, EdgeStyle.LOWERED ) );
			panel.add( label );
			label = new Label( "TR Borders" );
			label.setBorder( new EdgeBorder( EdgeStyle.LOWERED, EdgeStyle.NONE, EdgeStyle.NONE, EdgeStyle.LOWERED ) );
			panel.add( label );
			frame.getContentPane()
					.add( panel );

			panel = new TitledPanel( "Line Borders" );
			panel.setLayout( new java.awt.GridLayout( 0, 4, 10, 10 ) );
			label = new Label( "Top Border" );
			label.setBorder( new EdgeBorder( EdgeStyle.LINE, EdgeStyle.NONE, EdgeStyle.NONE, EdgeStyle.NONE ) );
			panel.add( label );
			label = new Label( "Left Border" );
			label.setBorder( new EdgeBorder( EdgeStyle.NONE, EdgeStyle.LINE, EdgeStyle.NONE, EdgeStyle.NONE ) );
			panel.add( label );
			label = new Label( "Bottom Border" );
			label.setBorder( new EdgeBorder( EdgeStyle.NONE, EdgeStyle.NONE, EdgeStyle.LINE, EdgeStyle.NONE ) );
			panel.add( label );
			label = new Label( "Right Border" );
			label.setBorder( new EdgeBorder( EdgeStyle.NONE, EdgeStyle.NONE, EdgeStyle.NONE, EdgeStyle.LINE ) );
			panel.add( label );
			label = new Label( "TL Borders" );
			label.setBorder( new EdgeBorder( EdgeStyle.LINE, EdgeStyle.LINE, EdgeStyle.NONE, EdgeStyle.NONE ) );
			panel.add( label );
			label = new Label( "BL Borders" );
			label.setBorder( new EdgeBorder( EdgeStyle.NONE, EdgeStyle.LINE, EdgeStyle.LINE, EdgeStyle.NONE ) );
			panel.add( label );
			label = new Label( "BR Borders" );
			label.setBorder( new EdgeBorder( EdgeStyle.NONE, EdgeStyle.NONE, EdgeStyle.LINE, EdgeStyle.LINE ) );
			panel.add( label );
			label = new Label( "TR Borders" );
			label.setBorder( new EdgeBorder( EdgeStyle.LINE, EdgeStyle.NONE, EdgeStyle.NONE, EdgeStyle.LINE ) );
			panel.add( label );
			frame.getContentPane()
					.add( panel );

			frame.pack();
			frame.setVisible( true );
		}
	}
}