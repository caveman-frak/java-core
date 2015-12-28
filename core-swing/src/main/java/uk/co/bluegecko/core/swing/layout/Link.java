package uk.co.bluegecko.core.swing.layout;


import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.util.HashSet;
import java.util.Set;


/**
 * Define a directional link between multiple component.
 */
public class Link extends Container
{

	private static final long serialVersionUID = -4645179700938913593L;

	private final Component component;
	private final Set< Component > components;
	private final Direction direction;

	protected Link( final Component component, final Set< Component > components, final Direction direction )
	{
		super();

		this.component = component;
		this.components = components;
		this.direction = direction;

		setLayout( new BorderLayout() );
		addImpl( component, null, -1 );
	}

	@Override
	public Component add( final Component component )
	{
		return null;
	}

	/**
	 * The base component.
	 * 
	 * @return base component
	 */
	public Component getComponent()
	{
		return component;
	}

	@Override
	public Dimension getPreferredSize()
	{
		return getEffectiveSize( components, component, direction, Size.PREFERRED );
	}

	@Override
	public Dimension getMinimumSize()
	{
		return getEffectiveSize( components, component, direction, Size.MIN );
	}

	@Override
	public Dimension getMaximumSize()
	{
		return getEffectiveSize( components, component, direction, Size.MAX );
	}

	private static Dimension getLinkedSize( final Set< Component > components, final Size size )
	{
		final Dimension linkedSize = new Dimension();

		for ( final Component component : components )
		{
			final Dimension componentSize = size.getSize( component );
			linkedSize.width = Math.max( linkedSize.width, componentSize.width );
			linkedSize.height = Math.max( linkedSize.height, componentSize.height );
		}
		return linkedSize;
	}

	private static Dimension getEffectiveSize( final Set< Component > components, final Component component,
			final Direction direction, final Size size )
	{
		final Dimension effectiveSize = new Dimension( size.getSize( component ) );
		final Dimension linkedSize = getLinkedSize( components, size );

		if ( direction.overideWidth() )
		{
			effectiveSize.width = linkedSize.width;
		}
		if ( direction.overrideHeight() )
		{
			effectiveSize.height = linkedSize.height;
		}
		return effectiveSize;
	}

	/**
	 * Create a link in one direction between the passed components.
	 * 
	 * @param direction
	 *            direction of link
	 * @param components
	 *            set of linked components
	 * @return a new link
	 */
	public static Link[] create( final Direction direction, final Component... components )
	{
		final Set< Component > linkedComponents = new HashSet< >();
		final Link[] links = new Link[components.length];

		int index = 0;
		for ( final Component component : components )
		{
			linkedComponents.add( component );
			links[index++] = new Link( component, linkedComponents, direction );
		}

		return links;
	}

	@SuppressWarnings( "javadoc" )
	public static final class Manager
	{

		private final Set< Component > components;
		private final Direction direction;

		public Manager( final Direction direction )
		{
			super();

			components = new HashSet< >();
			this.direction = direction;
		}

		public Link link( final Component component )
		{
			components.add( component );

			return new Link( component, components, direction );
		}

		public void unlink( final Component component )
		{
			components.remove( component );
		}

	}
}
