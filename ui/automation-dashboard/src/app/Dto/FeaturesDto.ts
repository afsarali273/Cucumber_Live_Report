export class FeaturesDto {

    featureName : string | undefined;
    featureUri: string | undefined;
    featureDescription: string | undefined;
    featureKeyWord: string | undefined;
    featureLineNumber: number | undefined;
    scenarios: object | undefined;
    featureStatus: string | undefined;
    duration: number | undefined;
    createdAt: Date | undefined;
}