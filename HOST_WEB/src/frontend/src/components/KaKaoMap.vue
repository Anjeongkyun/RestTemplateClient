<template>
  <div ref="map"></div>
</template>

<script>
/* global kakao */

export default {
  name: "KakaoMap",
  props: {
    width: {
      type: Number,
      default: 500
    },
    height: {
      type: Number,
      default: 400
    },
    center: {
      type: Array,
      required: true
    }
  },

  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=fb8991a4305fb55fdb6e41a311699be4&libraries=services,drawing";
      document.head.appendChild(script);
    }
  },

  data() {
    return {
      map: null,
      mapCenter: this.center,
      latitude: "",
      longitude: ""
    };
  },

  watch: {
    width(val) {
      this.$refs.map.style.width = val + "px";
      this.relayout();
    },
    height(val) {
      this.$refs.map.style.height = val + "px";
      this.relayout();
    }
  },

  methods: {
    initMap() {
      const container = this.$refs.map;
      container.style.width = this.width + "px";
      container.style.height = this.height + "px";

      const mapOptions = {
        center: new kakao.maps.LatLng(...this.mapCenter),
        level: 3
      };

      var map = new kakao.maps.Map(container, mapOptions);
      // map.setMapTypeId(kakao.maps.MapTypeId.HYBRID);
      console.log(this.mapCenter);

      var options = {
        // Drawing Manager를 생성할 때 사용할 옵션입니다
        map: map, // Drawing Manager로 그리기 요소를 그릴 map 객체입니다
        drawingMode: [
          // drawing manager로 제공할 그리기 요소 모드입니다
          kakao.maps.drawing.OverlayType.MARKER,
          kakao.maps.drawing.OverlayType.POLYLINE,
          kakao.maps.drawing.OverlayType.RECTANGLE,
          kakao.maps.drawing.OverlayType.CIRCLE,
          kakao.maps.drawing.OverlayType.POLYGON
        ],
        // 사용자에게 제공할 그리기 가이드 툴팁입니다
        // 사용자에게 도형을 그릴때, 드래그할때, 수정할때 가이드 툴팁을 표시하도록 설정합니다
        guideTooltip: ["draw", "drag", "edit"],
        markerOptions: {
          // 마커 옵션입니다
          draggable: true, // 마커를 그리고 나서 드래그 가능하게 합니다
          removable: true // 마커를 삭제 할 수 있도록 x 버튼이 표시됩니다
        },
        polylineOptions: {
          // 선 옵션입니다
          draggable: true, // 그린 후 드래그가 가능하도록 설정합니다
          removable: true, // 그린 후 삭제 할 수 있도록 x 버튼이 표시됩니다
          editable: true, // 그린 후 수정할 수 있도록 설정합니다
          strokeColor: "#39f", // 선 색
          hintStrokeStyle: "dash", // 그리중 마우스를 따라다니는 보조선의 선 스타일
          hintStrokeOpacity: 0.5 // 그리중 마우스를 따라다니는 보조선의 투명도
        },
        rectangleOptions: {
          draggable: true,
          removable: true,
          editable: true,
          strokeColor: "#39f", // 외곽선 색
          fillColor: "#39f", // 채우기 색
          fillOpacity: 0.5 // 채우기색 투명도
        },
        circleOptions: {
          draggable: true,
          removable: true,
          editable: true,
          strokeColor: "#39f",
          fillColor: "#39f",
          fillOpacity: 0.5
        },
        polygonOptions: {
          draggable: true,
          removable: true,
          editable: true,
          strokeColor: "#39f",
          fillColor: "#39f",
          fillOpacity: 0.5,
          hintStrokeStyle: "dash",
          hintStrokeOpacity: 0.5
        }
      };

      // 위에 작성한 옵션으로 Drawing Manager를 생성합니다
      var manager = new kakao.maps.drawing.DrawingManager(options);
      manager.select(kakao.maps.drawing.OverlayType["POLYGON"]);

      kakao.maps.event.addListener(map, "center_changed", this.onCenterChanged);
      this.map = map;

      // 지도를 클릭한 위치에 표출할 마커입니다
      var marker = new kakao.maps.Marker({
        // 지도 중심좌표에 마커를 생성합니다
        position: map.getCenter()
      });

      // 지도에 마커를 표시합니다
      marker.setMap(this.map);

      // 지도에 클릭 이벤트를 등록합니다
      // 지도를 클릭하면 마지막 파라미터로 넘어온 함수를 호출합니다
      kakao.maps.event.addListener(map, "rightclick", function(mouseEvent) {
        // 클릭한 위도, 경도 정보를 가져옵니다
        var latlng = mouseEvent.latLng;

        // 마커 위치를 클릭한 위치로 옮깁니다
        marker.setPosition(latlng);

        this.latitude = latlng.getLat();
        this.longitude = latlng.getLng();

        console.log(this.latitude);
        console.log(this.longitude);
      });
    },
    relayout() {
      kakao.maps.event.removeListener(
        this.map,
        "center_changed",
        this.onCenterChanged
      );
      this.map.relayout();
      const latlng = new kakao.maps.LatLng(...this.mapCenter);
      this.map.setCenter(latlng);
      kakao.maps.event.addListener(
        this.map,
        "center_changed",
        this.onCenterChanged
      );
    },
    onCenterChanged() {
      const center = this.map.getCenter();
      this.mapCenter = [center.getLat(), center.getLng()];
    }
  }
};
</script>
