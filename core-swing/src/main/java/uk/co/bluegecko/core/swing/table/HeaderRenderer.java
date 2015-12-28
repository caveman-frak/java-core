package uk.co.bluegecko.core.swing.table;


import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.UIManager;
import javax.swing.plaf.UIResource;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;


/**
 * Header renderer, supporting sorting.
 */
public class HeaderRenderer extends DefaultTableCellRenderer implements UIResource
{

	private static final int ARROW_HEIGHT = 10;
	private static final int ARROW_WIDTH = 10;
	private static final int ARROW_OFFSET = 4;
	private static final float ARROW_SCALING = 0.8f;

	private static final long serialVersionUID = -5994644134510216441L;

	private int sortIndex;
	private SortOrder sortOrder;
	private boolean multipleSortKeys;

	/**
	 * Create a new header renderer.
	 */
	public HeaderRenderer()
	{
		super();
	}

	@Override
	public Component getTableCellRendererComponent( final JTable table, final Object value, final boolean isSelected,
			final boolean hasFocus, final int rowIndex, final int columnIndex )
	{
		sortIndex = -1;
		sortOrder = SortOrder.UNSORTED;
		multipleSortKeys = false;

		if ( table != null )
		{
			final JTableHeader header = table.getTableHeader();
			if ( header != null )
			{
				setForeground( header.getForeground() );
				setBackground( header.getBackground() );
				setFont( header.getFont() );
			}

			final RowSorter< ? > rowSorter = table.getRowSorter();
			if ( rowSorter != null )
			{
				final List< ? extends RowSorter.SortKey > sortKeys = rowSorter.getSortKeys();
				int i = 0;
				for ( final RowSorter.SortKey sortKey : sortKeys )
				{
					i++;
					if ( table.convertColumnIndexToView( sortKey.getColumn() ) == columnIndex )
					{
						sortOrder = sortKey.getSortOrder();
						sortIndex = i;
						break;
					}
				}
				multipleSortKeys = sortKeys.size() > 1;
			}
		}
		setText( value == null ? "" : value.toString() );
		if ( hasSortKey() )
		{
			setBorder( BorderFactory.createCompoundBorder( UIManager.getBorder( "TableHeader.cellBorder" ),
					BorderFactory.createEmptyBorder( 0, 0, 0, ARROW_WIDTH + 2 * ARROW_OFFSET ) ) );
		}
		else
		{
			setBorder( UIManager.getBorder( "TableHeader.cellBorder" ) );
		}
		return this;
	}

	private boolean hasSortKey()
	{
		return sortOrder != SortOrder.UNSORTED;
	}

	private boolean hasMultipleSortKeys()
	{
		return multipleSortKeys;
	}

	@Override
	public void paint( final Graphics g )
	{
		super.paint( g );
		final Rectangle r = g.getClipBounds();
		if ( hasSortKey() )
		{
			final Color foreground = g.getColor();
			try
			{
				drawArrow( g, r );

				if ( hasMultipleSortKeys() )
				{
					final Font originalFont = g.getFont();
					try
					{
						g.setFont( originalFont.deriveFont( originalFont.getSize2D() * ARROW_SCALING ) );
						g.drawString( String.valueOf( sortIndex ), r.x + r.width - ( ARROW_WIDTH + ARROW_OFFSET * 2 ),
								r.y + r.height - ARROW_OFFSET );
					}
					finally
					{
						g.setFont( originalFont );
					}
				}
			}
			finally
			{
				g.setColor( foreground );
			}
		}
	}

	private void drawArrow( final Graphics g, final Rectangle r )
	{
		if ( hasSortKey() )
		{
			final Point origin = new Point( r.x + r.width - ( ARROW_OFFSET + ARROW_WIDTH ),
					r.y + ( r.height - ARROW_HEIGHT ) / 2 );
			g.translate( origin.x, origin.y );
			try
			{
				if ( sortOrder == SortOrder.ASCENDING )
				{
					g.setColor( SystemColor.controlLtHighlight );
					g.drawLine( ARROW_WIDTH / 2, 0, ARROW_WIDTH, ARROW_HEIGHT );
					g.drawLine( ARROW_WIDTH, ARROW_HEIGHT, 0, ARROW_HEIGHT );
					g.setColor( SystemColor.controlDkShadow );
					g.drawLine( 0, ARROW_HEIGHT, ARROW_WIDTH / 2, 0 );

				}
				else
				{
					g.setColor( SystemColor.controlLtHighlight );
					g.drawLine( ARROW_WIDTH / 2, ARROW_HEIGHT, ARROW_WIDTH, 0 );
					g.setColor( SystemColor.controlDkShadow );
					g.drawLine( ARROW_WIDTH, 0, 0, 0 );
					g.drawLine( 0, 0, ARROW_WIDTH / 2, ARROW_HEIGHT );
				}
			}
			finally
			{
				g.translate( -origin.x, -origin.y );
			}
		}
	}
}