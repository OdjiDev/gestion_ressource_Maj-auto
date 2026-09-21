export interface ApiError {
  status: number;
  message: string;
  errors?: Record<string, string[]>;
  timestamp?: string;
  path?: string;
}
