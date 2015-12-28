/**
 * Copyright 2009, <a href="http://bluegecko.co.uk/framework">Blue Gecko Limited</a>
 */
package uk.co.bluegecko.core.swing.layout;


import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Rectangle;


/**
 * Layout constraints
 */
public class Constraint
{

	/**
	 * The default {@link Scale} of 0.0 (or NONE).
	 */
	public static final Scale DEFAULT_FILL = Scale.NONE;
	/**
	 * The default {@link Alignment} of NORTHWEST.
	 */
	public static final Alignment DEFAULT_ALIGNMENT = Alignment.NORTHWEST;
	/**
	 * The default {@link java.awt.Insets}, of ( 0, 0, 0, 0 ).
	 */
	public static final Insets DEFAULT_INSETS = new Insets( 0, 0, 0, 0 );
	/**
	 * A default {@link Constraint}, with all properties set at their default values.
	 */
	protected static final Constraint DEFAULT = new Constraint();

	private final Insets insets;
	private final Alignment alignment;
	private final Scale horizontal;
	private final Scale vertical;

	/**
	 * Zero argument constructor, uses default values for alignment, fill and insets
	 */
	public Constraint()
	{
		this( DEFAULT_INSETS, DEFAULT_ALIGNMENT, DEFAULT_FILL, DEFAULT_FILL );
	}

	/**
	 * Build a {@link Constraint} with the specified parameters,
	 * using fills in double form.
	 *
	 * @param insets
	 *            - the insets for the cell.
	 * @param alignment
	 *            - the alignment for the component.
	 * @param horizontal
	 *            - the horizontal fill (as a double).
	 * @param vertical
	 *            - the vertical fill (as a double).
	 */
	public Constraint( final Insets insets, final Alignment alignment, final double horizontal, final double vertical )
	{
		this( insets, alignment, new Scale( horizontal ), new Scale( vertical ) );
	}

	/**
	 * Build a {@link Constraint} with the specified parameters.
	 *
	 * @param insets
	 *            - the insets for the cell.
	 * @param alignment
	 *            - the alignment for the component.
	 * @param horizontal
	 *            - the horizontal fill.
	 * @param vertical
	 *            - the vertical fill.
	 */
	public Constraint( final Insets insets, final Alignment alignment, final Scale horizontal, final Scale vertical )
	{
		this.insets = insets;
		this.alignment = alignment;
		this.horizontal = horizontal;
		this.vertical = vertical;
	}

	/**
	 * Build a {@link Constraint} with the specified parameters
	 * and defaulted horizontal and vertical fills (of NONE).
	 *
	 * @param insets
	 *            - the insets for the cell.
	 * @param alignment
	 *            - the alignment for the component.
	 */
	public Constraint( final Insets insets, final Alignment alignment )
	{
		this( insets, alignment, DEFAULT_FILL, DEFAULT_FILL );
	}

	/**
	 * Build a {@link Constraint} with the specified parameter
	 * and defaulted insets (of 0, 0, 0, 0 ) and horizontal and vertical fills (of NONE).
	 *
	 * @param alignment
	 *            - the alignment for the component.
	 */
	public Constraint( final Alignment alignment )
	{
		this( DEFAULT_INSETS, alignment );
	}

	/**
	 * Build a {@link Constraint} with defaulted insets (of 0, 0, 0, 0 )
	 * and alignment, and horizontal and vertical fills as specified.
	 *
	 * @param horizontal
	 *            - the horizontal fill.
	 * @param vertical
	 *            - the vertical fill.
	 */
	public Constraint( final Scale horizontal, final Scale vertical )
	{
		this( DEFAULT_INSETS, DEFAULT_ALIGNMENT, horizontal, vertical );
	}

	/**
	 * Get the insets.
	 *
	 * @return the insets
	 */
	public Insets getInsets()
	{
		return insets;
	}

	/**
	 * Get the alignment
	 *
	 * @return the alignment
	 */
	public Alignment getAlignment()
	{
		return alignment;
	}

	/**
	 * Get the horizontal scale.
	 *
	 * @return horizontal scale
	 */
	public Scale getHorizontal()
	{
		return horizontal;
	}

	/**
	 * Get the vertical scale.
	 *
	 * @return the vertical scale
	 */
	public Scale getVertical()
	{
		return vertical;
	}

	/**
	 * Apply insets to the passed rectangle.
	 *
	 * @param rect
	 *            rectangle to apply insets to
	 * @return adjusted rectangle
	 */
	public Rectangle applyInsets( final Rectangle rect )
	{
		if ( insets != null )
		{
			final Rectangle bounds = new Rectangle( rect.x + insets.left, rect.y + insets.top,
					rect.width - ( insets.left + insets.right ), rect.height - ( insets.top + insets.bottom ) );

			return bounds;
		}
		return rect;
	}

	/**
	 * Calculate the bounds for the component in the passed rectangle.
	 *
	 * @param rect
	 *            bounding rectangle
	 * @param component
	 *            component to size
	 * @return bound size of component
	 */
	public Rectangle calculateBounds( final Rectangle rect, final Component component )
	{
		final Rectangle bounds = applyInsets( rect );
		final Dimension size = component.getPreferredSize();

		size.width = getHorizontal().scale( bounds.width, size.width );
		size.height = getVertical().scale( bounds.height, size.height );

		return getAlignment().getBounds( bounds, size );
	}

	/**
	 * Return a new {@link Constraint} based on the current one, but with different
	 * insets.
	 *
	 * @param insets
	 *            - the insets to use for the new constraint.
	 * @return new constraint
	 */
	public Constraint clone( final Insets insets )
	{
		return new Constraint( insets, alignment, horizontal, vertical );
	}

	/**
	 * Return a new {@link Constraint} based on the current one, but with a different
	 * alignment.
	 *
	 * @param alignment
	 *            - the alignment to use for the new constraint.
	 * @return new constraint
	 */
	public Constraint clone( final Alignment alignment )
	{
		return new Constraint( insets, alignment, horizontal, vertical );
	}

	/**
	 * Return a new {@link Constraint} based on the current one, but with a different
	 * fills.
	 *
	 * @param horizontal
	 *            - the horizontal fill to use for the new constraint.
	 * @param vertical
	 *            - the vertical fill to use for the new constraint.
	 * @return new constraint
	 */
	public Constraint clone( final Scale horizontal, final Scale vertical )
	{
		return new Constraint( insets, alignment, horizontal, vertical );
	}

	/**
	 * Return a fully populated {@link Constraint} from the supplied set of
	 * constraints.
	 *
	 * @param grid
	 *            - the overall constraint for the entire grid.
	 * @param column
	 *            - the constraint for the selected column.
	 * @param row
	 *            - the constraint for the selected row.
	 * @param cell
	 *            - the constraint for the selected cell.
	 * @return - a fully populated constraint.
	 */
	public static final Constraint evaluateConstraints( final Constraint grid, final Constraint column,
			final Constraint row, final Constraint cell )
	{
		if ( cell != null )
		{
			return new Constraint( cell, row, column, grid );
		}
		else if ( row != null )
		{
			return new Constraint( row, column, grid );
		}
		else if ( column != null )
		{
			return new Constraint( column, grid );
		}
		else if ( grid != null )
		{
			return new Constraint( grid );
		}
		else
		{
			return DEFAULT;
		}
	}

	/**
	 * Construct a new constraint using the supplied list of constraints to provide non-null construction parameters.
	 *
	 * @param constraints
	 *            - the list of constraints to search.
	 */
	protected Constraint( final Constraint... constraints )
	{
		this( rollUpInsets( constraints ), rollUpAlignment( constraints ), rollUpHorizontal( constraints ),
				rollUpVertical( constraints ) );
	}

	/**
	 * Walk the list of {@link Constraint} until a non-null {@link java.awt.Insets} is
	 * found.
	 *
	 * @param constraints
	 *            - list of constraints to search.
	 * @return - the insets to use.
	 */
	protected static final Insets rollUpInsets( final Constraint... constraints )
	{
		for ( final Constraint constraint : constraints )
		{
			if ( constraint != null && constraint.getInsets() != null )
			{
				return constraint.getInsets();
			}
		}
		return DEFAULT_INSETS;
	}

	/**
	 * Walk the list of {@link Constraint} until a non-null
	 * {@link Alignment} is found.
	 *
	 * @param constraints
	 *            - list of constraints to search.
	 * @return - the vertical fill to use.
	 */
	protected static final Alignment rollUpAlignment( final Constraint... constraints )
	{
		for ( final Constraint constraint : constraints )
		{
			if ( constraint != null && constraint.getAlignment() != null )
			{
				return constraint.getAlignment();
			}
		}
		return DEFAULT_ALIGNMENT;
	}

	/**
	 * Walk the list of {@link Constraint} until a non-null horizontal
	 * {@link Scale} is found.
	 *
	 * @param constraints
	 *            - list of constraints to search.
	 * @return - the vertical fill to use.
	 */
	protected static final Scale rollUpHorizontal( final Constraint... constraints )
	{
		for ( final Constraint constraint : constraints )
		{
			if ( constraint != null && constraint.getHorizontal() != null )
			{
				return constraint.getHorizontal();
			}
		}
		return DEFAULT_FILL;
	}

	/**
	 * Walk the list of {@link Constraint} until a non-null vertical
	 * {@link Scale} is found.
	 *
	 * @param constraints
	 *            - list of constraints to search.
	 * @return - the vertical fill to use.
	 */
	protected static final Scale rollUpVertical( final Constraint... constraints )
	{
		for ( final Constraint constraint : constraints )
		{
			if ( constraint != null && constraint.getVertical() != null )
			{
				return constraint.getVertical();
			}
		}
		return DEFAULT_FILL;
	}

}
