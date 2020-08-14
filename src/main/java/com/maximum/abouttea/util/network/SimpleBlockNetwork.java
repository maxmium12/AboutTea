package com.maximum.abouttea.util.network;

import com.google.common.collect.Maps;
import com.google.common.collect.Multimaps;
import com.google.common.collect.SetMultimap;
import com.google.common.collect.Sets;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;

import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

public class SimpleBlockNetwork implements IBlockNetwork{
    private final Map<BlockPos, Set<BlockPos>> components;
    private final SetMultimap<BlockPos, Direction> connections;
    public SimpleBlockNetwork()
    {
        this.components = Maps.newHashMap();
        this.connections = Multimaps.newSetMultimap(Maps.newHashMap(), () -> EnumSet.noneOf(Direction.class));
    }
    @Override
    public int size(BlockPos node) {
        return components.containsKey(node) ? components.size() : 1;
    }

    @Override
    public BlockPos root(BlockPos node) {
        return components.containsKey(node) ? components.get(node).iterator().next() : node.toImmutable();
    }

    @Override
    public void cut(BlockPos node, Direction direction, ConnectivityListener afterSplit) {

    }

    @Override
    public void link(BlockPos node, Direction direction, ConnectivityListener beforeMerge) {
        BlockPos secondary = node.toImmutable();
        if (this.connections.put(secondary, direction))
        {
            BlockPos primary = node.offset(direction);
            this.connections.put(primary, direction.getOpposite());
            Set<BlockPos> primaryComponent = this.components.get(primary);
            Set<BlockPos> secondaryComponent = this.components.get(secondary);
            if (primaryComponent == null && secondaryComponent == null)
            {
                Set<BlockPos> union = Sets.newLinkedHashSet();
                beforeMerge.onChange(secondary, primary);
                this.components.put(secondary, union);
                this.components.put(primary, union);
                union.add(secondary);
                union.add(primary);
            }
            else if (primaryComponent == null)
            {
                beforeMerge.onChange(secondaryComponent.iterator().next(), primary);
                this.components.put(primary, secondaryComponent);
                secondaryComponent.add(primary);
            }
            else if (secondaryComponent == null)
            {
                beforeMerge.onChange(primaryComponent.iterator().next(), secondary);
                this.components.put(secondary, primaryComponent);
                primaryComponent.add(secondary);
            }
            else if (primaryComponent != secondaryComponent)
            {
                beforeMerge.onChange(primaryComponent.iterator().next(), secondaryComponent.iterator().next());
                Set<BlockPos> union = Sets.newLinkedHashSet(Sets.union(primaryComponent, secondaryComponent));
                union.forEach(pos -> this.components.put(pos, union));
            }
        }
    }
}
