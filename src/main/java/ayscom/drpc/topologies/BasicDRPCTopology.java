package ayscom.drpc.topologies;

/**
 * Created by lramirez on 13/07/15.
 */
import ayscom.drpc.bolts.ExclaimBolt;
import ayscom.drpc.bolts.NextBolt;
import ayscom.drpc.spouts.TestDRPCSpout;
import backtype.storm.Config;
import backtype.storm.ILocalDRPC;
import backtype.storm.LocalDRPC;
import backtype.storm.StormSubmitter;
import backtype.storm.drpc.DRPCInvocationsClient;
import backtype.storm.drpc.DRPCSpout;
import backtype.storm.drpc.LinearDRPCTopologyBuilder;
import backtype.storm.generated.DRPCExecutionException;
import backtype.storm.generated.DRPCRequest;
import backtype.storm.generated.DistributedRPC;
import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;
import org.apache.thrift7.TException;
import storm.trident.Stream;
import storm.trident.TridentTopology;

import java.util.Map;

/**
 * This topology is a basic example of doing distributed RPC on top of Storm. It implements a function that appends a
 * "!" to any string you send the DRPC function.
 * <p/>
 * See https://github.com/nathanmarz/storm/wiki/Distributed-RPC for more information on doing distributed RPC on top of
 * Storm.
 */
public class BasicDRPCTopology {


    public static void main(String[] args) throws Exception {
/*
        TopologyBuilder topologyBuilder= new TopologyBuilder();
        DRPCSpout spout;
        spout = new DRPCSpout("exclamation");
        topologyBuilder.setSpout("drpc", spout);
        topologyBuilder.setBolt("ExclaimBolt", new ExclaimBolt(), 1);
        topologyBuilder.setBolt("NextBolt", new NextBolt(), 1).shuffleGrouping("ExclaimBolt", "exclam");
        Config conf = new Config();
        conf.setNumWorkers(1);
        StormSubmitter.submitTopologyWithProgressBar(args[0], conf, topologyBuilder.createTopology());
*/

        LinearDRPCTopologyBuilder builder = new LinearDRPCTopologyBuilder("exclamation");
        builder.addBolt(new ExclaimBolt(), 1);

        builder.addBolt(new NextBolt(), 1).shuffleGrouping("exclam");
        Config conf = new Config();
        conf.setNumWorkers(1);
        StormSubmitter.submitTopologyWithProgressBar(args[0], conf, builder.createRemoteTopology());

    }

}