class Test{
    Specification<MemberData> spec = SpecBuilder.builder(MemberData.class)
            .ifTrue(searchRequest.isOnlyNotBlocked(),
                    () -> MemberDataSpecs.nonBlocked())
            .ifHasText(searchRequest.getName(),
                    name -> MemberDataSpecs.nameLike(sear chRequest.getName()))
            .toSpec();
    List<MemberData> result = memberDataDao.findAll(spec, PageRequest.of(0, 5));
}