/*
 * Created on 02-Nov-2005 Authored by pickardt TODO New class comments
 */
package uk.co.bluegecko.core.swing.table.rendering;


import java.awt.Component;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JComponent;
import javax.swing.UIManager;

import uk.co.bluegecko.core.swing.table.Table;


/**
 * Helper class for applying rendering hints to a cell component.
 */
public class RenderingHelper
{

	private RenderingHelper()
	{
		super();
	}

	/**
	 * Apply the hints to the cell component.
	 *
	 * @param table
	 *            the table
	 * @param component
	 *            the component
	 * @param isSelected
	 *            is the cell selected
	 * @param isFocused
	 *            is the cell focused
	 * @param isEditing
	 *            is the cell edited
	 * @param hints
	 *            collection of hints
	 */
	public static void updateComponent( final Table table, final Component component, final boolean isSelected,
			final boolean isFocused, final boolean isEditing, final Collection< RenderingHint< ? > > hints )
	{
		hints.addAll( getDefaultHints( table, isSelected, isFocused, isEditing ) );

		applyFontHint( component, hints );
		applyForegroundHint( component, hints );
		applyBackgroundHint( component, hints );
		applyBorderHint( component, hints );
		applyAlignmentHint( component, hints );
	}

	/**
	 * Get the row height based on rendering hints.
	 *
	 * @param table
	 *            the table
	 * @param isSelected
	 *            is the cell selected
	 * @param isFocused
	 *            is the cell focused
	 * @param isEditing
	 *            is the cell edited
	 * @param hints
	 *            collection of hints
	 * @return row height
	 */
	public static RowHeightHint getRowHeight( final Table table, final boolean isSelected, final boolean isFocused,
			final boolean isEditing, final Collection< RenderingHint< ? > > hints )
	{
		return ( RowHeightHint ) getPreferredHint( RenderingType.HEIGHT, hints );
	}

	private static void applyBorderHint( final Component component, final Collection< RenderingHint< ? > > hints )
	{
		final BorderHint hint = ( BorderHint ) getPreferredHint( RenderingType.BORDER, hints );
		if ( hint != null && component instanceof JComponent )
		{
			final JComponent comp = ( JComponent ) component;
			comp.setBorder( hint.getValue( comp.getBorder() ) );
		}
	}

	private static void applyBackgroundHint( final Component component, final Collection< RenderingHint< ? > > hints )
	{
		final BackgroundHint hint = ( BackgroundHint ) getPreferredHint( RenderingType.BACKGROUND, hints );
		if ( hint != null )
		{
			component.setBackground( hint.getValue( component.getBackground() ) );
		}
	}

	private static void applyForegroundHint( final Component component, final Collection< RenderingHint< ? > > hints )
	{
		final ForegroundHint hint = ( ForegroundHint ) getPreferredHint( RenderingType.FOREGROUND, hints );
		if ( hint != null )
		{
			component.setForeground( hint.getValue( component.getForeground() ) );
		}
	}

	private static void applyFontHint( final Component component, final Collection< RenderingHint< ? > > hints )
	{
		final FontHint hint = ( FontHint ) getPreferredHint( RenderingType.FONT, hints );
		if ( hint != null )
		{
			component.setFont( hint.getValue( component.getFont() ) );
		}
	}

	private static void applyAlignmentHint( final Component component, final Collection< RenderingHint< ? > > hints )
	{
		final AlignmentHint hint = ( AlignmentHint ) getPreferredHint( RenderingType.ALIGNMENT, hints );
		if ( hint != null )
		{
			hint.getValue().apply( component );

		}
	}

	protected static Collection< RenderingHint< ? > > getDefaultHints( final Table table, final boolean isSelected,
			final boolean isFocused, final boolean isEditing )
	{
		final Collection< RenderingHint< ? > > hints = getEmptyHints();
		hints.addAll( getBasicHints( table ) );
		if ( isSelected )
		{
			hints.addAll( getSelectedHints( table ) );
		}
		if ( isFocused && !isEditing )
		{
			hints.addAll( getFocusedHints( table, isSelected ) );
		}
		return hints;
	}

	protected static Collection< RenderingHint< ? > > getHints( final RenderingType type,
			final Collection< RenderingHint< ? > > hints )
	{
		final Collection< RenderingHint< ? > > results = getEmptyHints();
		for ( final RenderingHint< ? > hint : hints )
		{
			if ( hint.getType() == type )
			{
				results.add( hint );
			}
		}
		return results;
	}

	protected static RenderingHint< ? > getPreferredHint( final RenderingType type,
			final Collection< RenderingHint< ? > > hints )
	{
		RenderingHint< ? > result = null;

		for ( final RenderingHint< ? > hint : getHints( type, hints ) )
		{
			if ( result == null || hint.exceeds( result ) )
			{
				result = hint;
			}
		}

		return result;
	}

	private static Collection< RenderingHint< ? > > getBasicHints( final Table table )
	{
		final Collection< RenderingHint< ? > > hints = getEmptyHints();

		hints.add( new BackgroundHint( HintWeight.MIN_WEIGHT, table.getBackground() ) );
		hints.add( new ForegroundHint( HintWeight.MIN_WEIGHT, table.getForeground() ) );
		hints.add( new FontHint( HintWeight.MIN_WEIGHT, table.getFont() ) );

		return hints;
	}

	private static Collection< RenderingHint< ? > > getSelectedHints( final Table table )
	{
		final Collection< RenderingHint< ? > > hints = getEmptyHints();

		hints.add( new BackgroundHint( HintWeight.SELECTED_WEIGHT, table.getSelectionBackground() ) );
		hints.add( new ForegroundHint( HintWeight.SELECTED_WEIGHT, table.getSelectionForeground() ) );

		return hints;
	}

	/**
	 * @param table
	 */
	private static Collection< RenderingHint< ? > > getFocusedHints( final Table table, final boolean isSelected )
	{
		final Collection< RenderingHint< ? > > hints = getEmptyHints();

		hints.add(
				new BorderHint( HintWeight.FOCUSED_WEIGHT, UIManager.getBorder( "Table.focusCellHighlightBorder" ) ) );
		if ( !isSelected )
		{
			hints.add( new BackgroundHint( HintWeight.FOCUSED_WEIGHT,
					UIManager.getColor( "Table.focusCellBackground" ) ) );
			hints.add( new ForegroundHint( HintWeight.FOCUSED_WEIGHT,
					UIManager.getColor( "Table.focusCellForeground" ) ) );
		}

		return hints;
	}

	/**
	 * Get an empty mutable collection of hints.
	 *
	 * @return hint collection
	 */
	public static Collection< RenderingHint< ? > > getEmptyHints()
	{
		return new ArrayList< >();
	}

	/**
	 * Add a new hint to an empty collection.
	 *
	 * @param hint
	 *            hint to add
	 * @return hint collection
	 */
	public static Collection< RenderingHint< ? > > addHint( final RenderingHint< ? > hint )
	{
		return addHint( getEmptyHints(), hint );
	}

	/**
	 * Add a new hint to the collection.
	 *
	 * @param hints
	 *            existing hints
	 * @param hint
	 *            new hint
	 * @return hint collection
	 */
	public static Collection< RenderingHint< ? > > addHint( final Collection< RenderingHint< ? > > hints,
			final RenderingHint< ? > hint )
	{
		hints.add( hint );

		return hints;
	}

	/**
	 * Add new hints to the collection.
	 *
	 * @param hints
	 *            existing hints
	 * @param newHints
	 *            new hints
	 * @return hint collection
	 */
	public static Collection< RenderingHint< ? > > addHints( final Collection< RenderingHint< ? > > hints,
			final Collection< RenderingHint< ? > > newHints )
	{
		hints.addAll( newHints );

		return hints;
	}

}
