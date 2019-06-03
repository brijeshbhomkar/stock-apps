
function loadChart(id) {

$.getJSON('http://localhost:12020/api/nse/symbol/'+id, function (data) {
	
  // split the data set into ohlc and volume
  var ohlc = [],
    volume = [],
    signals = [],
    flags = [],
    dataLength = data.length,
    i = 0,
    j = 0;

  for (i; i < dataLength; i += 1) {
    ohlc.push([
    	data[i]["date"], // the date
        data[i]["open"], // open
        data[i]["high"], // high
        data[i]["low"], // low
        data[i]["close"], // close
    	data[i]["signal"] //signal up or down
    ]);

    volume.push([
    	 data[i]["date"], // the date
         data[i]["volume"] // the volume
    ]);
    
    signals.push([
    	data[i]["signal"] //find the signals
    ])
  }
  
  for(j; j<signals.length; j += 1) {
	  var signal = signals[j];
	  if (signal != 0) {
		  flags.push([signal])
	  }
  }
  
  Highcharts.stockChart('container', {
	
	 yAxis: [{
      labels: {
        align: 'left'
      },
      height: '80%',
      resize: {
        enabled: true
      }
    }, {
      labels: {
        align: 'left'
      },
      top: '80%',
      height: '20%',
      offset: 20
    }],
    tooltip: {
      shape: 'square',
      headerShape: 'callout',
      borderWidth: 0,
      shadow: false,
      positioner: function (width, height, point) {
        var chart = this.chart,
          position;

        if (point.isHeader) {
          position = {
            x: Math.max(
              // Left side limit
              chart.plotLeft,
              Math.min(
                point.plotX + chart.plotLeft - width / 2,
                // Right side limit
                chart.chartWidth - width - chart.marginRight
              )
            ),
            y: point.plotY
          };
        } else {
          position = {
            x: point.series.chart.plotLeft,
            y: point.series.yAxis.top - chart.plotTop
          };
        }

        return position;
      }
    },
    plotOptions: {
        ohlc: {
            color: 'red',
            upColor: 'green',
            lineWidth: 2
         }, 
         candlestick : {
        	 color: 'red',
             upColor: 'green',
             lineWidth: 1
         }
    },
    series: [{
      type: 'ohlc',
      id: id+'-ohlc',
      name: id,
      data: ohlc
    }, 
    {
      type: 'column',
      id: id+'-volume',
      name: id+' Volume',
      data: volume,
      yAxis: 1
    },
    {
        type: 'flags',
        data: [{
            x: flags[0],
            title: 'D',
            text: 'Down',
        },
        {
            x: flags[1],
            title: 'D',
            text: 'Down',
        }, 
        {
            x: flags[2],
            title: 'D',
            text: 'Down',
        }, {
            x: flags[3],
            title: 'D',
            text: 'Down',
        },
        {
            x: flags[4],
            title: 'D',
            text: 'Down',
        },
        {
            x: flags[5],
            title: 'D',
            text: 'Down',
        }, 
        {
            x: flags[6],
            title: 'D',
            text: 'Down',
        }, {
            x: flags[7],
            title: 'D',
            text: 'Down',
        },
        {
            x: flags[8],
            title: 'D',
            text: 'Down',
        },
        {
            x: flags[9],
            title: 'D',
            text: 'Down',
        }, 
        {
            x: flags[9],
            title: 'D',
            text: 'Down',
        }, {
            x: flags[10],
            title: 'D',
            text: 'Down',
        },
        {
            x: flags[11],
            title: 'D',
            text: 'Down',
        },
        {
            x: flags[12],
            title: 'D',
            text: 'Down',
        }, 
        {
            x: flags[13],
            title: 'D',
            text: 'Down',
        }, {
            x: flags[14],
            title: 'D',
            text: 'Down',
        },
        {
            x: flags[15],
            title: 'D',
            text: 'Down',
        },
        {
            x: flags[16],
            title: 'D',
            text: 'Down',
        }, 
        {
            x: flags[17],
            title: 'D',
            text: 'Down',
        }, {
            x: flags[18],
            title: 'D',
            text: 'Down',
        },
        {
            x: flags[19],
            title: 'D',
            text: 'Down',
        },
        {
            x: flags[20],
            title: 'D',
            text: 'Down',
        }, 
        {
            x: flags[21],
            title: 'D',
            text: 'Down',
        }, {
            x: flags[22],
            title: 'D',
            text: 'Down',
        },
        {
            x: flags[23],
            title: 'D',
            text: 'Down',
        },
        {
            x: flags[24],
            title: 'D',
            text: 'Down',
        }, 
        {
            x: flags[25],
            title: 'D',
            text: 'Down',
        }, {
            x: flags[26],
            title: 'D',
            text: 'Down',
        },
        {
            x: flags[27],
            title: 'D',
            text: 'Down',
        },
        {
            x: flags[28],
            title: 'D',
            text: 'Down',
        }, 
        {
            x: flags[29],
            title: 'D',
            text: 'Down',
        }, {
            x: flags[30],
            title: 'D',
            text: 'Down',
        },
        {
            x: flags[31],
            title: 'D',
            text: 'Down',
        },
        {
            x: flags[32],
            title: 'D',
            text: 'Down',
        }, 
        {
            x: flags[33],
            title: 'D',
            text: 'Down',
        }, {
            x: flags[34],
            title: 'D',
            text: 'Down',
        },
        {
            x: flags[35],
            title: 'D',
            text: 'Down',
        },
        {
            x: flags[36],
            title: 'D',
            text: 'Down',
        }, 
        {
            x: flags[37],
            title: 'D',
            text: 'Down',
        }, {
            x: flags[38],
            title: 'D',
            text: 'Down',
        },
        {
            x: flags[39],
            title: 'D',
            text: 'Down',
        },
        {
            x: flags[40],
            title: 'D',
            text: 'Down',
        }, 
        {
            x: flags[41],
            title: 'D',
            text: 'Down',
        }, {
            x: flags[41],
            title: 'D',
            text: 'Down',
        },
        {
            x: flags[42],
            title: 'D',
            text: 'Down',
        },
        {
            x: flags[43],
            title: 'D',
            text: 'Down',
        }, 
        {
            x: flags[44],
            title: 'D',
            text: 'Down',
        }, {
            x: flags[45],
            title: 'D',
            text: 'Down',
        },
        {
            x: flags[46],
            title: 'D',
            text: 'Down',
        },
        {
            x: flags[47],
            title: 'D',
            text: 'Down',
        }, 
        {
            x: flags[48],
            title: 'D',
            text: 'Down',
        }, {
            x: flags[49],
            title: 'D',
            text: 'Down',
        },
        {
            x: flags[50],
            title: 'D',
            text: 'Down',
        }
        ],
        onSeries: id+'-ohlc',
        shape: 'diamond',
        width: 20
    }, 
    ],
    responsive: {
      rules: [{
        condition: {
          maxWidth: 800
        },
        chartOptions: {
          rangeSelector: {
        	inputEnabled: true
          }
        }
      }]
    }
  });
});
}